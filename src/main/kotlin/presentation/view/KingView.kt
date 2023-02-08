package presentation.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun KingView(
    modifier: Modifier = Modifier
) {
    val cellSize = 50.dp
    val radius = 20.dp
    val strokeWidth = 4.dp
    Canvas(modifier = modifier.size(width = cellSize, height = cellSize)) {
        val radiusPx = radius.toPx()
        val strokeWidthPx = strokeWidth.toPx()
        val strokeWidthPx2 = strokeWidthPx * 2

        drawCircle(
            color = Color.Red,
            radius = radiusPx,
            style = Stroke(strokeWidthPx)
        )

        drawPath(
            path = Path().apply {
                moveTo(center.x, center.y - strokeWidthPx2)
                lineTo(center.x - strokeWidthPx, center.y - strokeWidthPx - radiusPx)
                lineTo(center.x + strokeWidthPx, center.y - strokeWidthPx - radiusPx)
                close()
            },
            color = Color.Red,
        )
        drawPath(
            path = Path().apply {
                moveTo(center.x + strokeWidthPx2, center.y)
                lineTo(center.x + strokeWidthPx + radiusPx, center.y - strokeWidthPx)
                lineTo(center.x + strokeWidthPx + radiusPx, center.y + strokeWidthPx)
                close()
            },
            color = Color.Red,
        )
        drawPath(
            path = Path().apply {
                moveTo(center.x, center.y + strokeWidthPx2)
                lineTo(center.x - strokeWidthPx, center.y + strokeWidthPx + radiusPx)
                lineTo(center.x + strokeWidthPx, center.y + strokeWidthPx + radiusPx)
                close()
            },
            color = Color.Red,
        )
        drawPath(
            path = Path().apply {
                moveTo(center.x - strokeWidthPx2, center.y)
                lineTo(center.x - strokeWidthPx - radiusPx, center.y - strokeWidthPx)
                lineTo(center.x - strokeWidthPx - radiusPx, center.y + strokeWidthPx)
                close()
            },
            color = Color.Red,
        )
    }
}