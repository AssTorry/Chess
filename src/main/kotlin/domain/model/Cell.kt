package domain.model

import kotlin.math.sign

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

operator fun Cell.minus(c: Cell) = Cell(x - c.x, y - c.y)

operator fun Cell.plus(c: Cell) = Cell(x + c.x, y + c.y)

/**
 * Соседняя правая клетка
 */
val Cell.right: Cell get() = copy(x = x + 1)

/**
 * Соседняя левая клетка
 */
val Cell.left: Cell get() = copy(x = x - 1)

/**
 * Соседняя верхняя клетка
 */
val Cell.up: Cell get() = copy(y = y - 1)

/**
 * Соседняя нижняя клетка
 */
val Cell.down: Cell get() = copy(y = y + 1)

val Cell.sign: Cell get() = Cell(x.sign, y.sign)

/**
 * Проверить, что координаты клетки в диапозоне [range]
 *
 * @param range диапазон проверки
 */
fun Cell.isInRange(range: IntRange): Boolean = range.contains(x) && range.contains(y)
