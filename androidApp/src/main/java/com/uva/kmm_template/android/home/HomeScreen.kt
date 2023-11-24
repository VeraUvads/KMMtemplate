package com.uva.kmm_template.android.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uva.kmm_template.android.utils.observeWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinViewModel(),
    navigateTo: (String) -> Unit, // todo builder
) {
    val state by homeViewModel.state.collectAsStateWithLifecycle()

    HomeContent(
        state = state,
        onAction = homeViewModel::sendAction,
    )

    homeViewModel.event.observeWithLifecycle { event ->
        when (event) {
            is HomeComponents.Event.NavigateTo -> {
                navigateTo(event.destination)
            }
        }
    }
}

@Composable
fun HomeContent(
    state: HomeComponents.State,
    onAction: (HomeComponents.Action) -> Unit,
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF161A23)),
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.Center),
            )
        } else if (state.isError) {
            Text(
                text = "Something went wrong",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(24.dp),
            )
            Button(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(24.dp),
                onClick = {
                    onAction(HomeComponents.Action.OnItemClick("it"))
                },
            ) {
                Text("Go to next non-working screen")
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 36.dp)
                    .verticalScroll(scrollState),
            ) {
                Text(
                    text = "Выберите тему",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                )

                Button(onClick = {
                    onAction(HomeComponents.Action.OnItemClick("it"))
                }) {
                }

                Spacer(modifier = Modifier.height(24.dp))
                state.items.forEachIndexed { index, item ->
                    ListItem(item = item, index = index + 1) {
                        onAction(HomeComponents.Action.OnItemClick(it))
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun ListItem(item: String, index: Int, onClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onClick(item)
            }
            .background(
                if (index % 2 == 0) {
                    Color(0xFF1F1F1F)
                } else {
                    Color(0xFF242835)
                },
            )
            .padding(16.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "$index.",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.width(32.dp),
            )

            Text(
                text = item,
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.weight(1f),
            )
        }
    }
}
