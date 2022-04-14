package com.example.oblig3_henrik.repository

import androidx.lifecycle.MutableLiveData
import com.example.oblig3_henrik.network.AlbumNetworkGet
import com.example.oblig3_henrik.ui.main.albums.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AlbumRepository {

    suspend fun getAlbums(userId: String): MutableLiveData<List<Album>> {
        var albums: List<Album>
        withContext(Dispatchers.IO) {
            albums = AlbumNetworkGet.albums.getAlbums()
            val cleanedList = mutableListOf<Album>()
            for (i in albums) {
                if (i.userId == userId) {
                    cleanedList.add(i)
                }
            }
            albums = cleanedList
        }
        return MutableLiveData(albums)
    }
}