package com.example.oblig3_henrik.repository

import androidx.lifecycle.MutableLiveData
import com.example.oblig3_henrik.network.PhotoNetworkGet
import com.example.oblig3_henrik.ui.main.photos.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotoRepository {

    suspend fun getPhotos(albumId: String): MutableLiveData<List<Photo>> {
        var photos: List<Photo>
        withContext(Dispatchers.IO) {
            photos = PhotoNetworkGet.photos.getPhotos()

            val cleanedList = mutableListOf<Photo>()
            for (i in photos) {
                if (i.albumId == albumId) {
                    cleanedList.add(i)
                }
            }
            photos = cleanedList
        }
        return MutableLiveData(photos)
    }
}