package com.acharyaprashantassignment.features.presentation

import android.graphics.Bitmap
import com.acharyaprashantassignment.features.domain.models.ImageThumbnail

data class UIState(
    var loading: Boolean = false,
//    var thumbnailState: ThumbnailState = ThumbnailState(),
    var thumbnails: List<ImageThumbnail> = mutableListOf(),

    )

data class ThumbnailState(
//    var imageThumbnail: ImageThumbnail? = null,
    val showRetry: Boolean = false,
    var showLoader: Boolean = false,
    var bitmap: Bitmap? = null
)
