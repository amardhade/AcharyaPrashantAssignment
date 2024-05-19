package com.acharyaprashantassignment.features.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.acharyaprashantassignment.comoponents.AsyncImage
import com.acharyaprashantassignment.utilites.HomeScreenEvent
import com.acharyaprashantassignment.utilites.LocalSpacing

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    uiState: UIState,
    thumbnailState: ThumbnailState,
    onEvent: (HomeScreenEvent) -> Unit
) {

    val context = LocalSpacing.current
    val localSpacing = LocalSpacing.current

//    val uiState = viewModel.homeScreenUIState.collectAsState()
    FlowColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        if (uiState.loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(localSpacing.dp_20),
                color = Color.Blue
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray),
                contentPadding = PaddingValues(localSpacing.dp_8),
                verticalArrangement = Arrangement.spacedBy(localSpacing.dp_8),
                horizontalArrangement = Arrangement.spacedBy(localSpacing.dp_8)
            ) {
                items(uiState.thumbnails, key = { item -> item.url }) { thumbnail ->
                    AsyncImage(
                        imageThumbnail = thumbnail,
                        thumbnailState = thumbnail.thumbnailsState,
                        onEvent = onEvent
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(UIState(loading = false), thumbnailState = ThumbnailState(), onEvent = {})
}