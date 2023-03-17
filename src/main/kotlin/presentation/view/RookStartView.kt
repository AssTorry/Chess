package presentation.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RookStartView(
    modifier: Modifier = Modifier
) {
    val cellSize = 50.dp
    Canvas(modifier = modifier.size(width = cellSize, height = cellSize)) {
        drawCircle(
            color = Color.Gray,
            radius = 8.dp.toPx()
        )
    }
}