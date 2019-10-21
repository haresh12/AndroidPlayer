package com.example.androidplayer

import com.google.gson.annotations.SerializedName


data class GifModel(
    @SerializedName("data")
    var `data`: List<Data>
)

data class Data(
    @SerializedName("images")
    val images: Images
)
data class Images(
    @SerializedName("original_mp4")
    val originalMp4: OriginalMp4,
    @SerializedName("480w_still")
    val wStill: WStill
)
data class OriginalMp4(
    @SerializedName("height")
    val height: String,
    @SerializedName("mp4")
    val mp4: String,
    @SerializedName("mp4_size")
    val mp4Size: String,
    @SerializedName("width")
    val width: String
)

data class WStill(
    @SerializedName("height")
    val height: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: String
)

data class Pagination(
    @SerializedName("count")
    val count: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total_count")
    val totalCount: Int
)