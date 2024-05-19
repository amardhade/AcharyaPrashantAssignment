package com.acharyaprashantassignment.features.presentation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acharyaprashantassignment.features.data.repos.APIImpl
import com.acharyaprashantassignment.features.domain.Result
import com.acharyaprashantassignment.features.domain.models.ImageThumbnail
import com.acharyaprashantassignment.utilites.HomeScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val apiImpl: APIImpl
) : ViewModel() {

    private var _homeScreenUIState = MutableStateFlow(UIState())
    val homeScreenUIState = _homeScreenUIState.asStateFlow()

    private var _thumbnailState = MutableStateFlow(ThumbnailState())
    val thumbnailState = _thumbnailState.asStateFlow()
    val systemCache = HashMap<String, Bitmap>()
    init {
        fetchData()
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.LoadImage -> {
                if (systemCache.containsKey(event.imageThumbnail.url)) {
                    loadImageThroughSystemCache(event.imageThumbnail.url, event.imageThumbnail)
                } else {
                    loadImageThroughNetwork(event.imageThumbnail, event.thumbnailState)
                }
            }
        }
    }

    private fun loadImageThroughSystemCache(keyAsUrl: String, imageThumbnail: ImageThumbnail) {
        var thumbnailState by mutableStateOf(ThumbnailState())
        Log.d("API", "loadImageThroughSystemCache: ${keyAsUrl}")
        viewModelScope.launch {
            thumbnailState = thumbnailState.copy(
                bitmap = systemCache.get(keyAsUrl)
            )
            imageThumbnail.thumbnailsState.value = thumbnailState
        }
    }

    private fun fetchData() {
        shouldShowLoader(true)
        viewModelScope.launch(Dispatchers.IO) {
            shouldShowLoader(true)
            val result: Result<Any?> = apiImpl.fetchImages()
            when (result) {
                Result.Loading -> {
                    Log.d("API", "Loading")
                }

                is Result.Failure -> {
                    shouldShowLoader(false)

                    Log.d("API", "Failed")
                }

                is Result.Success<*> -> {
                    shouldShowLoader(false)
                    val list = result.data as List<ImageThumbnail>
                    _homeScreenUIState.update { uiState ->
                        uiState.copy(
                            thumbnails = list
                        )
                    }
                    Log.d("API", "Success, size: ${list.size}")
                }
            }
        }
    }

    // https://cimg.acharyaprashant.org/images/img-9a39e8cf-58c9-408b-ad55-6010f7f8e5a9/0/image.jpg
    private fun loadImageThroughNetwork(
        imageThumbnail: ImageThumbnail,
        thumbnailState: MutableState<ThumbnailState>
    ) {
        val url = imageThumbnail.url
        if (url.isBlank()) return
        Log.d("API", "loadImageThroughNetwork: ${url}");
        viewModelScope.launch(Dispatchers.IO) {
            updateDownloadingBitmapStatus(true, imageThumbnail)

            val loadBitmap = async {
                try {
                    val byteArray: ByteArray = URL(url).openStream().readBytes()
                    BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            val bitmap = loadBitmap.await()
            updateDownloadingBitmapStatus(false, imageThumbnail)
            if (bitmap != null) {
                systemCache[url] = bitmap as Bitmap
                updateThumbnail(bitmap, imageThumbnail)
            } else {
                updateErrorPlaceholder(imageThumbnail)
            }
        }
    }

    private fun updateErrorPlaceholder(imageThumbnail: ImageThumbnail) {
        var thumbnailState by mutableStateOf(ThumbnailState())
        viewModelScope.launch {
            thumbnailState = thumbnailState.copy(
                bitmap = null,
                showRetry = true
            )
            imageThumbnail.thumbnailsState.value = thumbnailState
        }
    }

    private fun updateThumbnail(bitmap: Bitmap, imageThumbnail: ImageThumbnail) {
        var thumbnailState by mutableStateOf(ThumbnailState())
        viewModelScope.launch {
            thumbnailState = thumbnailState.copy(
                bitmap = bitmap,
                showRetry = false
            )
            imageThumbnail.thumbnailsState.value = thumbnailState
        }
    }

    private fun updateDownloadingBitmapStatus(loader: Boolean, imageThumbnail: ImageThumbnail) {
        var thumbnailState by mutableStateOf(imageThumbnail.thumbnailsState.value)
        viewModelScope.launch {
            thumbnailState = thumbnailState.copy(
                showLoader = loader
            )
            imageThumbnail.thumbnailsState.value = thumbnailState
        }
    }

    fun shouldShowLoader(shouldShow: Boolean) {
        viewModelScope.launch {
            _homeScreenUIState.update { uiState ->
                uiState.copy(loading = shouldShow)
            }
        }
    }

}