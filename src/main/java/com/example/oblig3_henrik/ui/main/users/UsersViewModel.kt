package com.example.oblig3_henrik.ui.main.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.oblig3_henrik.ServiceLocator
import kotlinx.coroutines.launch


class UsersViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = ServiceLocator.provideUserRepository(application)
    val users = userRepository.users


    fun databaseEmpty() {
        if (users.value == null)
            refreshDataFromRepository()
    }

    fun refreshDataFromRepository() {
        viewModelScope.launch {
            userRepository.refreshUsers()
        }
    }


}
