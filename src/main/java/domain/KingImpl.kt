package domain

import domain.model.*

/**
 * Реализация [King].
 */
class KingImpl : King() {
    /**
     * Текущая позиция короля.
     */
    private lateinit var kingPos: Cell

    /**
     * Смещение ладьи относительно стартовой позиции.
     */
    private lateinit var rookDelta: Cell

    /**
     * Предполагаемая стартовая позиция ладьи.
     */
    private lateinit var rookStartPos: Cell

    /**
     * Стортовые позиции где могла бы быть ладья.
     */
    private val rookStartPositions: MutableSet<Cell> = HashSet()

    override fun getRookStartPositions(): List<Cell> = rookStartPositions.toList()

    override fun onStart(position: Cell) {
        for (x in 0 until GameBoard.size) {
            for (y in 0 until GameBoard.size) {
                rookStartPositions.add(Cell(x, y))
            }
        }
        rookDelta = Cell()
        kingPos = position
        rookStartPositions.remove(position)
        rookStartPos = rookStartPositions.random()
    }

    override fun makeMove(lastRookMove: MoveType): Cell {
        updateRookDelta(lastRookMove)
        updateRookStartPositions()

        val rookCurrentPos = rookStartPos + rookDelta
        kingPos += (rookCurrentPos - kingPos).sign
        if (kingPos == rookCurrentPos) {
            rookStartPositions.remove(rookStartPos)
            rookStartPos = rookStartPositions.randomOrNull() ?: Cell()
        }
        return kingPos
    }

    private fun updateRookDelta(lastRookMove: MoveType) {
        rookDelta = when (lastRookMove) {
            MoveType.UP -> rookDelta.up
            MoveType.DOWN -> rookDelta.down
            MoveType.LEFT -> rookDelta.left
            MoveType.RIGHT -> rookDelta.right
            else -> error("Unknown rook move")
        }
    }

    private fun updateRookStartPositions() {
        if (rookStartPos + rookDelta == kingPos) {
            rookStartPositions.remove(rookStartPos)
        }
        val gameBoardRange = (0 until GameBoard.size)
        rookStartPositions.toList().forEach { rookStartPosition ->
            val rookCurrentPos = rookStartPosition + rookDelta
            if (!rookCurrentPos.isInRange(gameBoardRange)) {
                rookStartPositions.remove(rookStartPosition)
            }
        }
        if (!rookStartPositions.contains(rookStartPos)) {
            rookStartPos = rookStartPositions.randomOrNull() ?: (kingPos - rookDelta)
        }
    }
}