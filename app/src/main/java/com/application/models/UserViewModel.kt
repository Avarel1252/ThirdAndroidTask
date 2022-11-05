package com.application.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.getHardUsersList

class UserViewModel : ViewModel() {
    private val _userLiveData = MutableLiveData<List<UserModel>>()
    var userLiveData: LiveData<List<UserModel>> = _userLiveData

    init {
        _userLiveData.value = getHardUsersList().value
    }

    fun deleteUser(user: UserModel) {
        with(_userLiveData) {
            value = value?.minus(user)
        }
    }

    fun addUser(user: UserModel) {
        with(_userLiveData) {
            value = value?.plus(user)
        }
    }

    fun getLastId(): Int {
        val list = _userLiveData.value ?: listOf()
        return if (list.isEmpty()) {
            -1
        } else {
            list.last().id
        }
    }
}
