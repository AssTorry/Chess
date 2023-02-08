package presentation.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun RookView(
    modifier: Modifier = Modifier
) {
    val color = Color(0xFF813405)
    val cellSize = 50.dp
    Canvas(modifier = modifier.size(width = cellSize, height = cellSize)) {
        val offsetPx = 7.dp.toPx()
        val rookSizePx = 36.dp.toPx()
        val strokeWidthPx = 4.dp.toPx()

        drawCircle(
            color = color,
            radius = 8.dp.toPx()
        )
        drawArc(
            color = color,
            startAngle = 60f,
            sweepAngle = 60f,
            useCenter = false,
            topLeft = Offset(offsetPx, offsetPx),
            size = Size(rookSizePx, rookSizePx),
            style = Stroke(strokeWidthPx)
        )
        drawArc(
            color = color,
            startAngle = 150f,
            sweepAngle = 60f,
            useCenter = false,
            topLeft = Offset(offsetPx, offsetPx),
            size = Size(rookSizePx, rookSizePx),
            style = Stroke(strokeWidthPx)
        )
        drawArc(
            color = color,
            startAngle = -60f,
            sweepAngle = -60f,
            useCenter = false,
            topLeft = Offset(offsetPx, offsetPx),
            size = Size(rookSizePx, rookSizePx),
            style = Stroke(strokeWidthPx)
        )
        drawArc(
            color = color,
            startAngle = 30f,
            sweepAngle = -60f,
            useCenter = false,
            topLeft = Offset(offsetPx, offsetPx),
            size = Size(rookSizePx, rookSizePx),
            style = Stroke(strokeWidthPx)
        )
    }
}