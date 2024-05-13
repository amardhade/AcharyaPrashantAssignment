package com.acharyaprashantassignment.features.data

import com.acharyaprashantassignment.features.data.dtos.ImageDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("content/misc/media-coverages?limit=100")
    fun fetchImages(): Response<List<ImageDto>>
}