package com.example.oblig3_henrik.network

import com.example.oblig3_henrik.ServiceLocator
import com.example.oblig3_henrik.ui.main.albums.Album
import com.example.oblig3_henrik.ui.main.photos.Photo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.*

interface UserService {
    @GET("users")
    suspend fun getUsers(): NetworkUserContainer
}
interface AlbumService {
    @GET("albums")
    suspend fun getAlbums(): Call<List<Album>>
}
interface PhotoService {
    @GET("photos")
    suspend fun getPhotos(): Call<List<Photo>>
}
interface EditPhotoService {
    // For edit photo info
    @PUT("photos/{id}")
    @Headers("JsonUser-Agent: android; Content-type: application/json; charset=UTF-8")
    suspend fun updatePhoto(@Path("id") id: String, @Body photo: Photo): Response<ResponseBody>
}
interface DeletePhotoService {
    // For deletion of photo
    @DELETE("photos/{id}")
    @Headers("JsonUser-Agent: android")
    suspend fun deletePhoto(@Query("id") id: String): Response<ResponseBody>
}

object UserNetworkGet {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(ServiceLocator.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    
    val users: UserService = retrofit.create(UserService::class.java)
}
object UserNetworkChange {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(ServiceLocator.BASE_URL)
        .build()

    fun delete(id: String){
        retrofit.create(DeletePhotoService::class.java)
    }
    fun edit(id: String){
        retrofit.create(EditPhotoService::class.java)
    }
}

object AlbumNetwork {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(ServiceLocator.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val albums: AlbumService = retrofit.create(AlbumService::class.java)
}
object PhotoNetwork {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(ServiceLocator.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val photos: PhotoService = retrofit.create(PhotoService::class.java)
}
