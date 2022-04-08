package com.example.oblig3_henrik.ui.main.photos

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Photo(
    @Json(name = "albumId") val albumId: String,
    @Json(name = "id") val id: String,
    @Json(name = "title") var title: String,
    @Json(name = "url") val url: String,
    @Json(name = "thumbnailUrl") var thumbnailUrl: String
) : Parcelable
