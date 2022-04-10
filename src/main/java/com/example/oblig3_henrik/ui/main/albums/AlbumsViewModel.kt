package com.example.oblig3_henrik.ui.main.albums

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumsViewModel : ViewModel() {
    var albums = MutableLiveData<MutableList<Album>>()

    fun downloadAlbums(userId: String) {
        viewModelScope.launch {

        }
    }
}