package com.uva.kmm_template.android.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = koinViewModel()) {
    val state = homeViewModel.state.collectAsState()

    Column {
        AnimatedVisibility(visible = state.value.isLoading) {
            CircularProgressIndicator()
        }
        AnimatedVisibility(visible = !state.value.isLoading) {
            Button(onClick = { homeViewModel.start() }) {
                Text(text = "start")
            }
        }
        Text(text = state.value.data)
    }
}
