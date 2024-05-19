package com.acharyaprashantassignment.features.domain

import com.acharyaprashantassignment.utilites.UIText


sealed interface Result<T> {
    data class Success<T>(val data: T) : Result<Any?>
    data class Failure(val msg: UIText) : Result<Any?>
    data object Loading : Result<Any?>
}