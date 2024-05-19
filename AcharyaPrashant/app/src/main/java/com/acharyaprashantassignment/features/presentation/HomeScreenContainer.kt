package com.acharyaprashantassignment.features.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun HomeScreenContainer(viewModel: HomeScreenViewModel) {
    val uiState = viewModel.homeScreenUIState.collectAsState()
    val thumbnailState = viewModel.thumbnailState.collectAsState()
    HomeScreen(
        uiState = uiState.value,
        thumbnailState = thumbnailState.value,
        onEvent = { viewModel.onEvent(event = it) }
    )
}