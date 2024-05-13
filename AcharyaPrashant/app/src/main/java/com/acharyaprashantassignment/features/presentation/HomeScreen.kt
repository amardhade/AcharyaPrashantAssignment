package com.acharyaprashantassignment.features.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acharyaprashantassignment.utilites.LocalSpacing
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HomeScreen(
    uiState: UIState
) {

    val list = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
    val localSpacing = LocalSpacing.current

    if (uiState.loading) {
        Surface(
            modifier = Modifier.size(localSpacing.dp_20).fillMaxSize()
        ) {
            CircularProgressIndicator(modifier = Modifier.size(localSpacing.dp_20), color = Color.Blue)
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(localSpacing.dp_8),
        verticalArrangement = Arrangement.spacedBy(localSpacing.dp_8),
        horizontalArrangement = Arrangement.spacedBy(localSpacing.dp_8)
    ) {

        items(list) { item ->
            Surface(
                modifier = Modifier
                    .widthIn(min = 60.dp, max = 60.dp)
                    .heightIn(min = 60.dp, max = 60.dp)
            ) {}
        }

    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(UIState(loading = true))
}