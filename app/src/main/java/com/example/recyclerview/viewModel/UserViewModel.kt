package com.example.recyclerview.viewModel

import androidx.lifecycle.ViewModel
import com.example.recyclerview.data.LocalUserData
import com.example.recyclerview.model.User

class UserViewModel : ViewModel() {

    private val userList = ArrayList<User>()
    init {
        userList.addAll(LocalUserData().getLocalContactsList())
    }
    fun getUserList(): ArrayList<User> {
        return userList
    }

    fun deleteUser(index: Int) {
        userList.removeAt(index)
    }
    fun addUser(user: User, position: Int) {
        userList.add(position, user)
    }
}