package domain;

import domain.model.Cell;
import domain.model.MoveType;

/**
 * Шахматная фигура короля
 */
public abstract class King implements Figure {
    /**
     * Сделать ход королем
     *
     * @param lastRookMove направление последнего хода ладьи
     */
    public abstract Cell makeMove(MoveType lastRookMove);
}
