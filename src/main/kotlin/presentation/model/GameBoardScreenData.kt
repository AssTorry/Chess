package presentation.model

import domain.model.Cell

/**
 * Модель данных для игровой доски
 *
 * @param kingCell позиция короля
 * @param rookCell позиция ладьи
 */
data class GameBoardScreenData(
    val kingCell: Cell = Cell(),
    val rookCell: Cell = Cell(0, 1)
)