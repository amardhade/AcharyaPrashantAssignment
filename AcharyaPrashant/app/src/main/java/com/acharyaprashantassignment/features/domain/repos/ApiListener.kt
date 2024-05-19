package com.acharyaprashantassignment.features.domain.repos

import com.acharyaprashantassignment.features.domain.Result

interface ApiListener {

    suspend fun fetchImages(): Result<Any?>
}