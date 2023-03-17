package presentation.view

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import presentation.viewmodel.MainViewModel

@Composable
fun GameBoard(
    viewModel: MainViewModel,
    startKingPosition: IntOffset,
    startRookPosition: IntOffset,
) {
    val rookStartPositions by viewModel.getRookStartPositionsFlow().collectAsState(emptyList())
    for (pos in rookStartPositions) {
        RookStartView(modifier = Modifier.offset { pos })
    }
    GameBoardBackgroundView(
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
        ) { viewModel.stopGame() }
    )

    val animOffsetRook = remember { Animatable(startRookPosition, IntOffset.VectorConverter) }
    LaunchedEffect(Unit) {
        viewModel.getRookPositionFlow().collect { rookPosition ->
            animOffsetRook.animateTo(rookPosition)
            viewModel.onBoardAnimationEnd()
        }
    }
    RookView(modifier = Modifier.offset { animOffsetRook.value })

    val animOffsetKing = remember { Animatable(startKingPosition, IntOffset.VectorConverter) }
    LaunchedEffect(Unit) {
        viewModel.getKingPositionFlow().collect { kingPosition ->
            animOffsetKing.animateTo(kingPosition)
            viewModel.onBoardAnimationEnd()
        }
    }
    KingView(modifier = Modifier.offset { animOffsetKing.value })
}