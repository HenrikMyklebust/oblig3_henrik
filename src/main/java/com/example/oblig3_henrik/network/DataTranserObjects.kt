package com.example.oblig3_henrik.network

import com.example.oblig3_henrik.database.DatabaseUser
import com.example.oblig3_henrik.domain.DevByteUser
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkUserContainer(val users: List<NetworkUser>)

@JsonClass(generateAdapter = true)
data class NetworkUser(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "username") val username: String,
    @Json(name = "email") val email: String,
    @Json(name = "address") val address: Address,
    @Json(name = "phone") val phone: String,
    @Json(name = "website") val website: String,
    @Json(name = "company") val company: Company
)

@JsonClass(generateAdapter = true)
data class Address(
    @Json(name = "street") val street: String,
    @Json(name = "suite") val suite: String,
    @Json(name = "city") val city: String,
    @Json(name = "zipcode") val zipcode: String,
    @Json(name = "geo") val geo: Geo
)

@JsonClass(generateAdapter = true)
data class Geo(
    @Json(name = "lat") val lat: String,
    @Json(name = "lng") val lng: String
)

@JsonClass(generateAdapter = true)
data class Company(
    @Json(name = "name") val name: String,
    @Json(name = "catchPhrase") val catchPhrase: String,
    @Json(name = "bs") val bs: String
)

fun NetworkUserContainer.asDomainModel(): List<DevByteUser> {
    return users.map {
        DevByteUser(
            id = it.id,
            name = it.name,
            email = it.email
        )
    }
}

fun NetworkUserContainer.asDatabaseModel(): List<DatabaseUser> {
    return users.map {
        DatabaseUser(
            id = it.id,
            name = it.name,
            email = it.email
        )
    }
}