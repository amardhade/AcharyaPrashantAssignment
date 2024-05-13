package com.acharyaprashantassignment.features.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.acharyaprashantassignment.features.data.repos.APIImpl
import com.acharyaprashantassignment.features.domain.Result
import com.acharyaprashantassignment.features.domain.models.ImageThumbnail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val apiImpl: APIImpl
) : ViewModel() {

    private var _homeScreenUIState = MutableStateFlow(UIState())
    val homeScreenUIState = _homeScreenUIState.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            shouldShowLoader(true)
            val result: Result = apiImpl.fetchImages()
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
                    Log.d("API", "Success, size: ")
                }
            }
        }
    }

    private fun shouldShowLoader(shouldShow: Boolean) {
        viewModelScope.launch {
            _homeScreenUIState.update { uiState ->
                uiState.copy(loading = shouldShow)
            }
        }
    }


}