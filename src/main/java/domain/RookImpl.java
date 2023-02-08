package domain;

import domain.model.Cell;
import domain.model.CellKt;

/**
 * Реализация {@link Rook}.
 */
public class RookImpl extends Rook {
    /**
     * Текущая позиция.
     */
    private Cell position;

    @Override
    public void onStart(Cell position) {
        this.position = position;
    }

    @Override
    public Cell makeMove(Cell kingPosition) {
        //TODO реализовать логику рандомной ходьбы по доске
        position = CellKt.right(position);
        return position;
    }
}
