package domain.model

/**
 * Клетка доски
 *
 * @property x координата х
 * @property y координата у
 */
data class Cell(
    val x: Int = 0,
    val y: Int = 0
)

/**
 * Проверить, что координаты клетки в диапозоне [range]
 *
 * @param range диапазон проверки
 */
fun Cell.coordinatesInRange(range: IntRange): Boolean = range.contains(x) && range.contains(y)

/**
 * Соседняя левая клетка
 */
fun Cell.left(): Cell = copy(x = x + 1, y = y)