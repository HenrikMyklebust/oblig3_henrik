package com.example.oblig3_henrik.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.oblig3_henrik.database.DatabaseUser
import com.example.oblig3_henrik.database.UsersDatabase
import com.example.oblig3_henrik.database.asDomainModel
import com.example.oblig3_henrik.domain.DevByteUser
import com.example.oblig3_henrik.network.UserNetworkGet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class UserRepository(private val database: UsersDatabase) {

    val users: LiveData<List<DevByteUser>> = Transformations.map(database.UserDao.getUsers()) {
        it.asDomainModel()
    }

    suspend fun refreshUsers() {
        withContext(Dispatchers.IO) {
            val users = UserNetworkGet.users.getUsers()
            database.UserDao.insertAll(users.map {
                DatabaseUser(
                    id = it.id,
                    name = it.name,
                    email = it.email
                )
            })
        }
    }
}
