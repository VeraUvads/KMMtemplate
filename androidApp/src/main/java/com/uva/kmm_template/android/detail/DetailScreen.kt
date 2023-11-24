package com.uva.kmm_template.android.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
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
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(viewModel: DetailViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()

    DetailContent(
        state = state,
        onAction = viewModel::sendAction
    )
}

@Composable
fun DetailContent(
    state: DetailComponents.State,
    onAction: (DetailComponents.Action) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF161A23)) // todo theme
    ) {
        when {
            state.isError -> {
                Text(
                    text = "Что-то пошло не так",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(24.dp)
                )
            }

            state.isLoading -> {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.Center)
                )
            }

            else -> {
                Text(text = state.data)
            }
        }
    }
}
