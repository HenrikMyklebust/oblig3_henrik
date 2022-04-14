package com.example.oblig3_henrik.ui.main.photos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oblig3_henrik.ServiceLocator
import kotlinx.coroutines.launch

class PhotosViewModel : ViewModel() {

    private val photoRepository = ServiceLocator.providePhotoRepository()
    var photos = MutableLiveData<List<Photo>>()

    fun downloadPhotos(albumId: String) {
        viewModelScope.launch {
            photos.value = photoRepository.getPhotos(albumId).value
        }
    }


}