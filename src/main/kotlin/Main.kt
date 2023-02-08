import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import domain.*
import domain.model.Cell
import kotlinx.coroutines.MainScope
import presentation.converter.CellToIntOffsetConverter
import presentation.converter.OneWayConverter
import presentation.view.DialogView
import presentation.view.GameBoard
import presentation.view.GameBoardStatic
import presentation.viewmodel.MainViewModel
import java.util.*

fun main() = application {
    val cellSizePx = with(LocalDensity.current) { 50.dp.roundToPx() }
    val converter: OneWayConverter<Cell, IntOffset> = CellToIntOffsetConverter(cellSizePx)

    val viewModel = MainViewModel(
        king = KingImpl(),
        rook = RookImpl(),
        random = Random(),
        gameBoard = GameBoardImpl(),
        scope = MainScope(),
        converter
    )

    Window(
        title = "Поймай меня, если сможешь...",
        onCloseRequest = ::exitApplication,
        resizable = false,
        state = rememberWindowState(
            position = WindowPosition.Aligned(Alignment.Center),
            size = DpSize(600.dp, 600.dp)
        ),
    ) {
        MaterialTheme {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFFEDEFF1)),
                contentAlignment = Alignment.Center
            ) {
                val mainScreenData by viewModel.getMainScreenFlow().collectAsState()
                val kingPosition = converter.convert(mainScreenData.gameBoard.kingCell)
                val rookPosition = converter.convert(mainScreenData.gameBoard.rookCell)

                if (mainScreenData.isDialogVisible) {
                    GameBoardStatic(kingPosition, rookPosition)
                    DialogView(viewModel)
                } else {
                    GameBoard(viewModel, kingPosition, rookPosition)
                }
            }
        }
    }
}
