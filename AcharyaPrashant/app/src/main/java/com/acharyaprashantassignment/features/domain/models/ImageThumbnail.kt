package com.acharyaprashantassignment.features.domain.models

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.acharyaprashantassignment.features.presentation.ThumbnailState

data class ImageThumbnail(
    val id: String = "",
    var index: Int = 0,
    val title: String = "",
    val url: String = "",
    val downloadingBitmap: Boolean = false,
    val bitmap: Bitmap? = null,
    var thumbnailsState: MutableState<ThumbnailState> = mutableStateOf(ThumbnailState())
)
