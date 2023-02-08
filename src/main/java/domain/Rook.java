package domain;

import domain.model.Cell;

/**
 * Шахматная фигура ладьи
 */
public abstract class Rook implements Figure {
    /**
     * Сделать ход ладьей
     *
     * @param kingPosition клетка где стоит сейчас король
     */
    public abstract Cell makeMove(Cell kingPosition);
}
