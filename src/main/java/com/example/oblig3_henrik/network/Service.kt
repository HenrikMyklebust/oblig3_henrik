package com.example.oblig3_henrik.network

import com.example.oblig3_henrik.ServiceLocator.retrofit
import com.example.oblig3_henrik.ui.main.albums.Album
import com.example.oblig3_henrik.ui.main.photos.Photo
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface UserService {
    @GET("users")
    suspend fun getUsers(): List<NetworkUser>
}

interface AlbumService {
    @GET("albums")
    suspend fun getAlbums(): List<Album>
}

interface PhotoService {
    @GET("photos")
    suspend fun getPhotos(): List<Photo>
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
    val users: UserService = retrofit.create(UserService::class.java)
}

object AlbumNetworkGet {
    val albums: AlbumService = retrofit.create(AlbumService::class.java)
}

object PhotoNetworkGet {
    val photos: PhotoService = retrofit.create(PhotoService::class.java)
}

object PhotoNetworkChange {
    fun delete(): DeletePhotoService {
        return retrofit.create(DeletePhotoService::class.java)
    }

    fun edit(): EditPhotoService {
        return retrofit.create(EditPhotoService::class.java)
    }
}
