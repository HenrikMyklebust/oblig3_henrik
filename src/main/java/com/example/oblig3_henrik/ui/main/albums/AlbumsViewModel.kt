package com.example.oblig3_henrik.ui.main.albums

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oblig3_henrik.ServiceLocator
import kotlinx.coroutines.launch

class AlbumsViewModel : ViewModel() {

    private val albumRepository = ServiceLocator.provideAlbumRepository()
    var albums = MutableLiveData<List<Album>>()

    fun downloadAlbums(userId: String) {
        viewModelScope.launch {
            albums.value = albumRepository.getAlbums(userId).value

        }
    }
}