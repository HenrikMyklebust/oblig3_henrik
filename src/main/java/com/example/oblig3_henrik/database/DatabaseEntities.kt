package com.example.oblig3_henrik.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.oblig3_henrik.domain.DevByteUser

@Entity
data class DatabaseUser constructor(
    @PrimaryKey
    val id: String,
    val name: String,
    val email: String
)


fun List<DatabaseUser>.asDomainModel(): List<DevByteUser> {
    return map {
        DevByteUser(
            id = it.id,
            name = it.name,
            email = it.email
        )
    }
}