package presentation.converter

import androidx.compose.ui.unit.IntOffset
import domain.model.Cell

/**
 * Конвертeр из ячейки доски в координаты главного экрана.
 * Начало координат в центре экрана
 */
class CellToIntOffsetConverter(
    private val cellSizePx: Int
) : OneWayConverter<Cell, IntOffset> {

    override fun convert(from: Cell): IntOffset = IntOffset(
        (from.x - 3) * cellSizePx - cellSizePx / 2,
        (from.y - 3) * cellSizePx - cellSizePx / 2
    )
}