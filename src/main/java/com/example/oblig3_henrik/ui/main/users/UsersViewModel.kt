package com.example.oblig3_henrik.ui.main.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.oblig3_henrik.database.getDatabase
import com.example.oblig3_henrik.repository.UserRepository
import kotlinx.coroutines.launch


class UsersViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(getDatabase(application))
    val users = userRepository.users

    fun refreshDataFromRepository() {
        viewModelScope.launch {
                userRepository.refreshUsers()
        }
    }
}
