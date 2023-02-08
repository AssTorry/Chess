package presentation.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke.Companion.DefaultMiter
import androidx.compose.ui.unit.dp

@Composable
fun GameBoardBackgroundView(
    modifier: Modifier = Modifier
) {
    val cellSize = 50.dp
    val cornerRadius = 12.dp
    val color = Color(0xFFF9A03F)
    Canvas(modifier = modifier.size(width = 500.dp, height = 500.dp)) {
        val width = size.width
        val height = size.height
        val cellSizePx = cellSize.toPx()
        val lastCellWidth = width - cellSizePx
        val lastCellHeight = height - cellSizePx
        val cornerRadiusPx = cornerRadius.toPx()
        //линии сетки
        for (i in (1..9)) {
            //горизонтальные полоски
            drawLine(
                color,
                Offset(0f, cellSizePx * i),
                Offset(cellSizePx, cellSizePx * i),
            )
            drawLine(
                color,
                Offset(cellSizePx, cellSizePx * i),
                Offset(lastCellWidth, cellSizePx * i),
                strokeWidth = DefaultMiter
            )
            drawLine(
                color,
                Offset(lastCellWidth, cellSizePx * i),
                Offset(width, cellSizePx * i),
            )
            //Вертикальные полоски
            drawLine(
                color,
                Offset(cellSizePx * i, 0f),
                Offset(cellSizePx * i, cellSizePx),
            )
            drawLine(
                color,
                Offset(cellSizePx * i, cellSizePx),
                Offset(cellSizePx * i, lastCellHeight),
                strokeWidth = DefaultMiter
            )
            drawLine(
                color,
                Offset(cellSizePx * i, lastCellHeight),
                Offset(cellSizePx * i, height),
            )
        }
        //Закругления на перекрестах линий
        for (i in (2..8)) {
            for (j in (2..8)) {
                val centerX = cellSizePx * i
                val centerY = cellSizePx * j
                drawPath(
                    path = Path().apply {
                        moveTo(centerX, centerY - cornerRadiusPx)
                        quadraticBezierTo(
                            centerX, centerY,
                            centerX + cornerRadiusPx, centerY
                        )
                        quadraticBezierTo(
                            centerX, centerY,
                            centerX, centerY + cornerRadiusPx
                        )
                        quadraticBezierTo(
                            centerX, centerY,
                            centerX - cornerRadiusPx, centerY
                        )
                        quadraticBezierTo(
                            centerX, centerY,
                            centerX, centerY - cornerRadiusPx
                        )
                        close()
                    },
                    color = color,
                )
            }
        }
    }
}