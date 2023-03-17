package domain

import domain.model.*
import kotlin.random.Random

/**
 * Реализация [Rook].
 */
class RookImpl : Rook() {

    /**
     * Текущая позиция.
     */
    private lateinit var rookPosition: Cell

    override fun onStart(position: Cell) {
        rookPosition = position
    }

    override fun makeMove(kingPosition: Cell): Cell {
        val gameBoardRange = (0 until GameBoard.size)
        var newRookPosition = getRandomMove()
        while (!newRookPosition.isInRange((gameBoardRange)) || newRookPosition == kingPosition) {
            newRookPosition = getRandomMove()
        }
        rookPosition = newRookPosition
        return rookPosition
    }

    private fun getRandomMove(): Cell = when (Random.nextInt(4)) {
        0 -> rookPosition.right
        1 -> rookPosition.left
        2 -> rookPosition.up
        else -> rookPosition.down
    }
}