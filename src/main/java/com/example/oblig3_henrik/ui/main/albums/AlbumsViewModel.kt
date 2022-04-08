package com.example.oblig3_henrik.ui.main.albums

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oblig3_henrik.ui.main.RetrofitInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumsViewModel : ViewModel() {
    var albums = MutableLiveData<MutableList<Album>>()

    suspend fun downloadAlbums(userId: String) {
        withContext(Dispatchers.IO) {
            val retrofitInterface = RetrofitInterface.create().getAlbums()

            retrofitInterface.enqueue(object : Callback<List<Album>> {
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