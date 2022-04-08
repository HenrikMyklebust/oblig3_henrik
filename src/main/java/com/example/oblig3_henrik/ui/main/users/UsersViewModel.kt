package com.example.oblig3_henrik.ui.main.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oblig3_henrik.ui.main.RetrofitInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UsersViewModel : ViewModel() {

    var users = MutableLiveData<MutableList<User>>()

    suspend fun downloadUsers() {
        withContext(Dispatchers.IO) {
            val retrofitInterface = RetrofitInterface.create().getUsers()

            retrofitInterface.enqueue(object : Callback<List<User>> {
                override fun onResponse(
                    call: Call<List<User>>?,
                    response: Response<List<User>>?
                ) {
                    if (response?.body() != null) {
                        users.value = (response.body() as MutableList<User>?)!!
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                }
            })
        }
    }
}
