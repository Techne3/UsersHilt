package com.example.usershilt.networks.service

import com.example.usershilt.networks.models.UserModel
import retrofit2.Response
import retrofit2.http.GET

interface UserService {



    @GET("users")
    suspend fun getUsers(): Response<List<UserModel>>


}