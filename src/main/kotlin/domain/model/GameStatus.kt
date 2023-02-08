package domain.model

/**
 * Статус игры
 */
sealed class GameStatus {
    /**
     * Все ок, игра начилась или продолжается
     */
    object NORMAL : GameStatus()

    /**
     * Конец игры, король поймал ладью
     */
    object SUCCESS : GameStatus()

    /**
     * Что-то пошло не так
     *
     * @property message сообщение ошибки
     */
    data class ERROR(
        val message: String
    ) : GameStatus()

    /**
     * Игра была остановлена
     */
    object STOPPED : GameStatus()
}