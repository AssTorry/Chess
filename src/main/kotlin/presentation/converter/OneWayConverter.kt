package presentation.converter

/**
 * Конвертeр из [F] в [T].
 */
interface OneWayConverter<F, T> {
    /**
     * Конвертировать [F] в [T]
     *
     * @param from исходный класс для конвертации
     * @return [T] конечный класс после конвертации
     */
    fun convert(from: F): T
}