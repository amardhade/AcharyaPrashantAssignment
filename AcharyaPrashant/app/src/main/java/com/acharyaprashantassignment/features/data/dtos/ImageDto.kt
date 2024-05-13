package com.acharyaprashantassignment.features.data.dtos

import com.google.gson.annotations.SerializedName

data class ImageDto(

    @SerializedName("id"            ) var id            : String?        = null,
    @SerializedName("title"         ) var title         : String?        = null,
    @SerializedName("thumbnail"     ) var thumbnail     : Thumbnail?     = Thumbnail()
)

data class Thumbnail(
    @SerializedName("id"          ) var id          : String?        = null,
    @SerializedName("version"     ) var version     : Int?           = null,
    @SerializedName("domain"      ) var domain      : String?        = null,
    @SerializedName("basePath"    ) var basePath    : String?        = null,
    @SerializedName("key"         ) var key         : String?        = null,
    @SerializedName("qualities"   ) var qualities   : List<Int> = arrayListOf(),
    @SerializedName("aspectRatio" ) var aspectRatio : Double?        = null
)
