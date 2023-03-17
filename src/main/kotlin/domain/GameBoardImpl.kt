package domain

import domain.model.*
import domain.model.GameStatus.NORMAL
import domain.model.GameStatus.SUCCESS
import domain.model.GameStatus.ERROR
import domain.model.GameStatus.STOPPED
import domain.model.FigureType
import extensions.runIf
import kotlin.math.abs

/**
 * Реализация [GameBoard]
 */
class GameBoardImpl : GameBoard {
    override var kingPosition: Cell = Cell()
        private set
    override var rookPosition: Cell = Cell()
        private set
    override var gameStatus: GameStatus = NORMAL
        private set
    override var lastFigureMoved: FigureType = FigureType.UNKNOWN
        private set
    override var lastRookMove: MoveType = MoveType.UNKNOWN
        private set

    override fun onStart(startKingCell: Cell, startRookCell: Cell) {
        this.kingPosition = startKingCell
        this.rookPosition = startRookCell
        lastFigureMoved = FigureType.UNKNOWN
        lastRookMove = MoveType.UNKNOWN
        updateGameStatus()
    }

    override fun onFigureMove(newPosition: Cell, figure: FigureType) = runIf(gameStatus == NORMAL) {
        lastFigureMoved = figure
        when (figure) {
            FigureType.KING -> onKingMove(newPosition)
            FigureType.ROOK -> onRookMove(newPosition)
            FigureType.UNKNOWN -> {
                gameStatus = ERROR("Неизвестный тип фигуры")
            }
        }
    }

    override fun onStop() = runIf(gameStatus !is ERROR) {
        gameStatus = STOPPED
    }

    /**
     * Ход короля
     *
     * @param cell новая клетка фигуры
     */
    private fun onKingMove(cell: Cell) {
        val xDiff = abs(cell.x - kingPosition.x)
        val yDiff = abs(cell.y - kingPosition.y)
        kingPosition = cell

        if (xDiff > 1 || yDiff > 1 || xDiff + yDiff == 0) {
            gameStatus = ERROR("Король двигается не по правилам!")
        } else {
            updateGameStatus()
        }
    }

    /**
     * Ход ладьи
     *
     * @param cell новая клетка фигуры
     */
    private fun onRookMove(cell: Cell) {
        val xDiff = cell.x - rookPosition.x
        val yDiff = cell.y - rookPosition.y
        rookPosition = cell

        lastRookMove = getRookMove(xDiff, yDiff)

        if (abs(xDiff) + abs(yDiff) != 1) {
            lastRookMove = MoveType.UNKNOWN
            gameStatus = ERROR("Ладья двигается не по правилам!")
        } else {
            updateGameStatus()
        }
    }

    /**
     * Получить направление движения ладьи
     */
    private fun getRookMove(xDiff: Int, yDiff: Int): MoveType = when {
        yDiff < 0 -> MoveType.UP
        yDiff > 0 -> MoveType.DOWN
        xDiff < 0 -> MoveType.LEFT
        xDiff > 0 -> MoveType.RIGHT
        else -> MoveType.UNKNOWN
    }

    /**
     * Обновить статус игры
     */
    private fun updateGameStatus() {
        gameStatus = when {
            !kingPosition.isInRange((0 until GameBoard.size)) -> ERROR("Король за пределами доски!")
            !rookPosition.isInRange((0 until GameBoard.size)) -> ERROR("Ладья за пределами доски!")
            kingPosition == rookPosition -> SUCCESS
            else -> NORMAL
        }
    }
}