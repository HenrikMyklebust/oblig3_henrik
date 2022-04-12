package com.example.oblig3_henrik.ui.main.photo

import androidx.lifecycle.ViewModel
import com.example.oblig3_henrik.network.PhotoNetworkChange
import com.example.oblig3_henrik.ui.main.photos.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class PhotoViewModel : ViewModel() {

    suspend fun editPhoto(photo: Photo): Boolean {
        val response: Boolean
        withContext(Dispatchers.IO) {
            val userNetworkEdit = PhotoNetworkChange.edit().updatePhoto(photo.id, photo)
            response = userNetworkEdit.isSuccessful
        }
        return response
    }

    suspend fun deletePhoto(id: String): Boolean {
        val response: Boolean
        withContext(Dispatchers.IO) {
            val userNetworkDelete = PhotoNetworkChange.delete().deletePhoto(id)
            response = userNetworkDelete.isSuccessful
        }
        return response
    }
}