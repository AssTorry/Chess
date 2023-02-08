package domain;

import domain.model.Cell;

/**
 * Шахматная фигура
 */
public interface Figure {
    /**
     * Старт игры
     *
     * @param position начальная позиция фигуры
     */
    void onStart(Cell position);
}
