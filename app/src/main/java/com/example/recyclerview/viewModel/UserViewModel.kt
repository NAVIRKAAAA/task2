package com.example.recyclerview.viewModel

import androidx.lifecycle.ViewModel
import com.example.recyclerview.data.LocalUserData
import com.example.recyclerview.model.User

class UserViewModel : ViewModel() {

    private val users = ArrayList<User>()

    init {
        users.addAll(LocalUserData().getLocalContactsList())
    }

    fun getUserList(): ArrayList<User> {
        return users
    }

    fun deleteUser(index: Int) {
        users.removeAt(index)
    }

    fun addUser(user: User, position: Int) {
        users.add(position, user)
    }
}