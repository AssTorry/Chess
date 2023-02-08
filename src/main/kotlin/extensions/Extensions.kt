package extensions

/**
 * Выполнять [block] если [predicate] = true
 */
internal inline fun <T> T.runIf(predicate: Boolean, block: T.() -> Unit) {
    if (predicate) block()
}
