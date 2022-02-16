package com.example.usershilt.networks.repository

import android.util.Log
import com.example.usershilt.features.view.TAG
import com.example.usershilt.networks.models.UserModel
import com.example.usershilt.networks.service.UserService
import com.example.usershilt.utils.Resource
import java.lang.Exception
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val userService: UserService
) {

    suspend fun getAllUsers(): Resource<List<UserModel>> {
        return try {
            val response = userService.getUsers()
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Failed to get All Users.")
            }
        } catch (ex: Exception) {
            Resource.Error("unexpected error")
        }
    }

}