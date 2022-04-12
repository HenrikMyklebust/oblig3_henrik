package com.example.oblig3_henrik

import android.app.Application
import com.example.oblig3_henrik.database.getDatabase
import com.example.oblig3_henrik.repository.AlbumRepository
import com.example.oblig3_henrik.repository.PhotoRepository
import com.example.oblig3_henrik.repository.UserRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ServiceLocator {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    fun provideUserRepository(application: Application): UserRepository {
        return UserRepository(getDatabase(application))
    }

    fun provideAlbumRepository(): AlbumRepository {
        return AlbumRepository()
    }

    fun providePhotoRepository(): PhotoRepository {
        return PhotoRepository()
    }

}