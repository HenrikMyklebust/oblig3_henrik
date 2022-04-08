package com.example.oblig3_henrik.ui.main.photo

import androidx.lifecycle.ViewModel
import com.example.oblig3_henrik.ui.main.RetrofitInterface
import com.example.oblig3_henrik.ui.main.photos.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class PhotoViewModel : ViewModel() {

    suspend fun editPhoto(photo: Photo): Boolean {
        val response: Boolean
        withContext(Dispatchers.IO) {
            val retrofitInterface = RetrofitInterface.create().updatePhoto(photo.id, photo)
            response = retrofitInterface.isSuccessful
        }
        return response
    }

    suspend fun deletePhoto(photoId: String): Boolean {
        val response: Boolean
        withContext(Dispatchers.IO) {
            val retrofitInterface = RetrofitInterface.delete().deletePhoto(photoId)
            response = retrofitInterface.isSuccessful
        }
        return response
    }
}