package com.example.usershilt.features.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.usershilt.features.view.TAG
import com.example.usershilt.networks.models.UserModel
import com.example.usershilt.networks.repository.UserRepository
import com.example.usershilt.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.RuntimeException
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private var _users: MutableLiveData<Resource<List<Any>>> = MutableLiveData()
    val users: LiveData<Resource<List<Any>>> get() = _users


    init {
        getUsers()
    }


    private fun getUsers() {
        _users.value = Resource.Loading
        viewModelScope.launch {
            val response = userRepository.getAllUsers()
            Log.d(TAG, "here is the response -> $response")
            _users.postValue(response)
        }
    }

}