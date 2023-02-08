package presentation.model

/**
 * Модель данных для главного экрана
 */
data class MainScreenData(
    val isDialogVisible : Boolean = true,
    val gameBoard: GameBoardScreenData = GameBoardScreenData(),
)