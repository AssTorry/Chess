package domain;

import domain.model.Cell;
import domain.model.CellKt;
import domain.model.MoveType;

/**
 * Реализация {@link King}.
 */
public class KingImpl extends King {
    /**
     * Текущая позиция.
     */
    private Cell position;

    @Override
    public void onStart(Cell position) {
        this.position = position;
    }

    @Override
    public Cell makeMove(MoveType lastRookMove) {
        //TODO реализовать логику поиска ладьи
        position = CellKt.left(position);
        return position;
    }
}
