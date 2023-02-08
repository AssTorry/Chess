package presentation.view

import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset

@Composable
fun GameBoardStatic(
    startKingPosition: IntOffset,
    startRookPosition: IntOffset,
) {
    GameBoardBackgroundView()
    RookView(modifier = Modifier.offset { startRookPosition })
    KingView(modifier = Modifier.offset { startKingPosition })
}