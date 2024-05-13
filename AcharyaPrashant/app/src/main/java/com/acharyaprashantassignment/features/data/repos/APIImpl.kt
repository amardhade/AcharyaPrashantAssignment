package com.acharyaprashantassignment.features.data.repos

import com.acharyaprashantassignment.R
import com.acharyaprashantassignment.features.data.ApiService
import com.acharyaprashantassignment.features.data.dtos.ImageDto
import com.acharyaprashantassignment.features.domain.Result
import com.acharyaprashantassignment.features.domain.models.ImageThumbnail
import com.acharyaprashantassignment.features.domain.repos.ApiListener
import com.acharyaprashantassignment.utilites.UIText
import retrofit2.Response
import javax.inject.Inject

class APIImpl @Inject constructor(private val apiService: ApiService) : ApiListener {
    override suspend fun fetchImages(): Result {
        var result: Result = Result.Loading

        result = try {
            val response = apiService.fetchImages()
            if (response.isSuccessful && response.body() != null)
                Result.Success(data = toImageThumbnails(response.body() as List<ImageDto>))
            else Result.Failure(msg = UIText.DynamicString(response.message()))
        } catch (e: Exception) {
            Result.Failure(msg = UIText.StringResource(R.string.prblm_connecting_with_server))
        }
        return result
    }


    private fun toImageThumbnails(list: List<ImageDto>): List<ImageThumbnail> {
        val thumbnails = mutableListOf<ImageThumbnail>()
        for(item in list) {
            thumbnails.add(
                ImageThumbnail(
                    id = item.id ?: "",
                    title = item.title ?: "",
                    url = "${item.thumbnail?.domain}/${item.thumbnail?.basePath}/0/${item.thumbnail?.key}"
                )
            )
        }
        return thumbnails
    }

}