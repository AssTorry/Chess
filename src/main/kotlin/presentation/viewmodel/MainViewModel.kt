package presentation.viewmodel

import androidx.compose.ui.unit.IntOffset
import domain.GameBoard
import domain.King
import domain.Rook
import domain.model.Cell
import domain.model.FigureType.KING
import domain.model.FigureType.ROOK
import domain.model.GameStatus
import domain.model.GameStatus.NORMAL
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import presentation.converter.OneWayConverter
import presentation.model.GameBoardScreenData
import presentation.model.MainScreenData
import kotlin.random.Random

/**
 * ViewModel для игры короля и ладьи
 */
class MainViewModel(
    private val king: King,
    private val rook: Rook,
    private val gameBoard: GameBoard,
    private val scope: CoroutineScope,
    private val converter: OneWayConverter<Cell, IntOffset>
) {
    private val gameStatusFlow = MutableStateFlow(gameBoard.gameStatus)
    private val mainScreenFlow = MutableStateFlow(MainScreenData())
    private val kingPositionFlow = MutableSharedFlow<IntOffset>(replay = 1)
    private val rookPositionFlow = MutableSharedFlow<IntOffset>(replay = 1)
    private val rookStartPositionFlow = MutableSharedFlow<List<IntOffset>>(replay = 1)

    /**
     * Запустить игру
     */
    fun startGame() {
        val randomStart = generateRandomStart()
        hideDialog(randomStart)
        king.onStart(randomStart.kingCell)
        rook.onStart(randomStart.rookCell)
        gameBoard.onStart(randomStart.kingCell, randomStart.rookCell)
        makeMove()
    }

    /**
     * Остановить игру
     */
    fun stopGame() {
        resetReplayCache()
        gameBoard.onStop()
        showDialog()
    }

    /**
     * Колбек на конец анимации доски
     */
    fun onBoardAnimationEnd() = if (gameBoard.gameStatus != NORMAL) {
        resetReplayCache()
        showDialog()
    } else {
        makeMove()
    }

    fun getGameStatusFlow(): StateFlow<GameStatus> = gameStatusFlow
    fun getMainScreenFlow(): StateFlow<MainScreenData> = mainScreenFlow
    fun getKingPositionFlow(): SharedFlow<IntOffset> = kingPositionFlow
    fun getRookPositionFlow(): SharedFlow<IntOffset> = rookPositionFlow
    fun getRookStartPositionsFlow(): SharedFlow<List<IntOffset>> = rookStartPositionFlow

    /**
     * Сделать ход
     */
    private fun makeMove() {
        if (gameBoard.lastFigureMoved != ROOK) {
            moveRook()
        } else {
            moveKing()
        }
    }

    /**
     * Показать шахматную доску
     */
    private fun hideDialog(randomStart: GameBoardScreenData) {
        mainScreenFlow.value = MainScreenData(false, randomStart)
    }

    /**
     * Скрыть шахматную доску
     */
    private fun showDialog() {
        gameStatusFlow.value = gameBoard.gameStatus
        mainScreenFlow.value = MainScreenData(
            true,
            GameBoardScreenData(
                gameBoard.kingPosition,
                gameBoard.rookPosition,
            )
        )
    }

    /**
     * Сделать ход ладьей
     */
    private fun moveRook() = scope.launch(Dispatchers.Main) {
        val newPosition = rook.makeMove(gameBoard.kingPosition)
        gameBoard.onFigureMove(newPosition, ROOK)

        gameStatusFlow.value = gameBoard.gameStatus
        rookPositionFlow.emit(converter.convert(newPosition))
    }

    /**
     * Сделать ход королем
     */
    private fun moveKing() = scope.launch(Dispatchers.Main) {
        val newPosition = king.makeMove(gameBoard.lastRookMove)
        gameBoard.onFigureMove(newPosition, KING)

        gameStatusFlow.value = gameBoard.gameStatus
        rookStartPositionFlow.emit(king.rookStartPositions.map(converter::convert))
        kingPositionFlow.emit(converter.convert(newPosition))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun resetReplayCache() {
        kingPositionFlow.resetReplayCache()
        rookPositionFlow.resetReplayCache()
        rookStartPositionFlow.resetReplayCache()
    }

    private fun generateRandomStart(): GameBoardScreenData {
        val startKing = Random.nextInt(64)
        var startRook = Random.nextInt(64)

        while (startRook == startKing) {
            startRook = Random.nextInt(64)
        }

        return GameBoardScreenData(
            kingCell = Cell(startKing % 8, startKing / 8),
            rookCell = Cell(startRook % 8, startRook / 8),
        )
    }
}