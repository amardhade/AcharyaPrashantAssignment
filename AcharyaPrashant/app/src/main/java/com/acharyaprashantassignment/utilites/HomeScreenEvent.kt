package com.acharyaprashantassignment.utilites

import androidx.compose.runtime.MutableState
import com.acharyaprashantassignment.features.domain.models.ImageThumbnail
import com.acharyaprashantassignment.features.presentation.ThumbnailState

sealed interface HomeScreenEvent {

    data class LoadImage(
        val imageThumbnail: ImageThumbnail,
        val thumbnailState: MutableState<ThumbnailState>
    ) : HomeScreenEvent
}