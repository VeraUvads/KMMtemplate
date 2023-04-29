package com.uva.kmm_template.android.home

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = koinViewModel()) {
    val state = homeViewModel.state.collectAsState()

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF161A23))
    ) {
        when (state.value) {
            is HomeComponents.State.Loading -> {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.Center)
                )
            }

            is HomeComponents.State.Success -> {
                val items = (state.value as HomeComponents.State.Success).items // TODO

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 36.dp)
                        .verticalScroll(scrollState)
                ) {
                    Text(
                        text = "Выберите тему",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    items.forEachIndexed { index, item ->
                        MyListItem(item = item, index = index + 1, itemsCount = items.size)
                    }
                }
            }

            is HomeComponents.State.Error -> {
                Text(
                    text = (state.value as HomeComponents.State.Error).message.orEmpty(),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(24.dp)
                )
            }
        }
    }
}

@Composable
fun MyListItem(item: String, index: Int, itemsCount: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(16.dp))
            .background(
                if (index % 2 == 0) {
                    Color(0xFF1F1F1F)
                } else {
                    Color(0xFF242835)
                }
            )
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "$index.",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.width(32.dp)
            )

            Text(
                text = item,
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
