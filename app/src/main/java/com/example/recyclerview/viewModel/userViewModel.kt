package com.example.recyclerview.viewModel

import androidx.lifecycle.ViewModel
import com.example.recyclerview.contactAdapter.RecyclerViewAdapter
import com.example.recyclerview.data.LocalUserData
import com.example.recyclerview.model.User
import androidx.recyclerview.widget.RecyclerView

class userViewModel : ViewModel() {
    private lateinit var adapter: RecyclerViewAdapter

    private val userList = ArrayList<User>()
    init {

        userList.addAll(LocalUserData().getLocalContactsList())
    }
    fun getUserList(): ArrayList<User> {
        return userList
    }

    fun deleteUser(user: User) {

    }
}