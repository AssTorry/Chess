package presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.model.GameStatus.NORMAL
import domain.model.GameStatus.SUCCESS
import domain.model.GameStatus.ERROR
import domain.model.GameStatus.STOPPED
import presentation.viewmodel.MainViewModel

@Composable
fun DialogView(
    viewModel: MainViewModel
) = Box(
    modifier = Modifier
        .size(400.dp, 400.dp)
        .background(color = Color.White.copy(alpha = 0.6f)),
    contentAlignment = Alignment.Center
) {
    val gameStatus by viewModel.getGameStatusFlow().collectAsState()
    Column {
        val text = when (val status = gameStatus) {
            is NORMAL -> "Попытка не пытка"
            is SUCCESS -> "SUCCESS Король поймал ладью!"
            is ERROR -> status.message
            is STOPPED -> "Игра была прервана"
        }
        val color = when (gameStatus) {
            is SUCCESS -> Color.Green
            is ERROR -> Color.Red
            else -> Color.Black
        }
        Text(
            text = text,
            color = color,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                viewModel.startGame()
            }) {
            Text("Start game!")
        }
    }
}