package com.acharyaprashantassignment.features.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreenContainer() {
    val viewModel = hiltViewModel() as HomeScreenViewModel
    val uiState = viewModel.homeScreenUIState.collectAsState()
    HomeScreen(
        uiState = uiState.value
    )
}