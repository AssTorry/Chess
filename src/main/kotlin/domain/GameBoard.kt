package domain

import domain.model.Cell
import domain.model.GameStatus
import domain.model.FigureType
import domain.model.MoveType

/**
 * Шахматная доска
 */
interface GameBoard {
    /**
     * Размер доски
     */
    val size: Int

    /**
     * Текущая клетка с королем
     */
    val kingPosition: Cell

    /**
     * Текущая клетка с ладьей
     */
    val rookPosition: Cell

    /**
     * Статус игры
     */
    val gameStatus: GameStatus

    /**
     * Какая фигура ходила последней
     */
    val lastFigureMoved: FigureType

    /**
     * Последний ход ладьи
     */
    val lastRookMove: MoveType

    /**
     * Старт новой игры
     *
     * @param startKingCell стартовая клетка короля
     * @param startRookCell стартовая клетка ладьи
     */
    fun onStart(startKingCell: Cell, startRookCell: Cell)

    /**
     * [фигура][figure] сделала ход в [newPosition]
     */
    fun onFigureMove(newPosition: Cell, figure: FigureType)

    /**
     * Игра была завершена
     */
    fun onStop()
}