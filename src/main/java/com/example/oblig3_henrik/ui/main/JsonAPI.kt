package com.example.oblig3_henrik.ui.main

import com.example.oblig3_henrik.ui.main.albums.Album
import com.example.oblig3_henrik.ui.main.photos.Photo
import com.example.oblig3_henrik.ui.main.users.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface RetrofitInterface {

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("albums")
    fun getAlbums(): Call<List<Album>>

    @GET("photos")
    fun getPhotos(): Call<List<Photo>>

    // For edit photo info
    @PUT("photos/{id}")
    @Headers("User-Agent: android; Content-type: application/json; charset=UTF-8")
    suspend fun updatePhoto(@Path("id") id: String, @Body photo: Photo): Response<ResponseBody>

    // For deletion of photo
    @DELETE("photos/{id}")
    @Headers("User-Agent: android")
    suspend fun deletePhoto(@Query("id") id: String): Response<ResponseBody>


    companion object {
        fun create(): RetrofitInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()
            return retrofit.create(RetrofitInterface::class.java)
        }

        fun delete(): RetrofitInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()
            return retrofit.create(RetrofitInterface::class.java)
        }
    }


}