package com.example.usershilt.features.viewModel

import androidx.lifecycle.*
import com.example.usershilt.networks.models.UserModel
import com.example.usershilt.networks.repository.UserRepository
import com.example.usershilt.utils.Resource
import kotlinx.coroutines.launch
import java.lang.RuntimeException
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val UserRepository:UserRepository
) :ViewModel(){

    private var _users: MutableLiveData<Resource<List<UserModel>>> = MutableLiveData()
    val users: LiveData<Resource<List<UserModel>>> = _users



    init{
        getUsers()
    }


    private fun getUsers(){
        _users.value = Resource.Loading
        viewModelScope.launch {
            val response = UserRepository.getAllUsers()
            _users.postValue(response)
        }
    }

    class Factory(
        private val userRepository: UserRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                return UserViewModel(userRepository) as T
            } else {
                throw RuntimeException("Could not create instance of UsersViewModel")
            }
        }

    }
}