package com.acharyaprashantassignment.features.domain

import com.acharyaprashantassignment.utilites.UIText


sealed interface Result {
    data class Success<T>(val data: T) : Result
    data class Failure(val msg: UIText) : Result
    data object Loading : Result
}