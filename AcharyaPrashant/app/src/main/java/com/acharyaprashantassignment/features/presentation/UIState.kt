package com.acharyaprashantassignment.features.presentation

import com.acharyaprashantassignment.features.domain.models.ImageThumbnail

data class UIState(
    var loading: Boolean = false,
    var thumbnails: List<ImageThumbnail> = mutableListOf()
)
