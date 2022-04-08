package com.example.oblig3_henrik.ui.main.photos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oblig3_henrik.ui.main.RetrofitInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosViewModel : ViewModel() {
    var photos = MutableLiveData<MutableList<Photo>>()

    suspend fun downloadPhotos(albumId: String) {
        withContext(Dispatchers.IO) {
            val retrofitInterface = RetrofitInterface.create().getPhotos()

            retrofitInterface.enqueue(object : Callback<List<Photo>> {
                override fun onResponse(
                    call: Call<List<Photo>>?,
                    response: Response<List<Photo>>?
                ) {
                    if (response?.body() != null) {
                        photos.value = (response.body() as MutableList<Photo>?)!!
                        val cleanedList = mutableListOf<Photo>()
                        for (i in photos.value!!) {
                            if (i.albumId == albumId) {
                                cleanedList.add(i)
                            }
                        }
                        photos.value = cleanedList
                    }
                }

                override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                }
            })
        }
    }
}