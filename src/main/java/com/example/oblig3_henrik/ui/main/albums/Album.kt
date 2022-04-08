package com.example.oblig3_henrik.ui.main.albums

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Album(
    @Json(name = "userId") val userId: String,
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String
)
