package com.example.oblig3_henrik.repository

import androidx.lifecycle.MutableLiveData
import com.example.oblig3_henrik.network.AlbumNetwork
import com.example.oblig3_henrik.ui.main.albums.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class AlbumRepository {

    val albums = MutableLiveData<MutableList<Album>>()

    suspend fun getAlbums(userId: String) {
        withContext(Dispatchers.IO){
            val albumNetwork = AlbumNetwork.albums.getAlbums()
            albumNetwork.enqueue(object : Callback<List<Album>> {
                override fun onResponse(
                    call: Call<List<Album>>?,
                    response: Response<List<Album>>?
                ) {
                    if (response?.body() != null) {
                        albums.value = (response.body() as MutableList<Album>?)!!
                        val cleanedList = mutableListOf<Album>()
                        for (i in albums.value!!) {
                            if (i.userId == userId) {
                                cleanedList.add(i)
                            }
                        }
                        albums.value = cleanedList
                    }
                }

                override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                }
            })
        }
    }
}