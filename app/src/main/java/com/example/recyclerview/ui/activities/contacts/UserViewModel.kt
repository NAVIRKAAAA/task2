package com.example.recyclerview.ui.activities.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.recyclerview.domain.localdataset.LocalUserData
import com.example.recyclerview.domain.dataclass.User

//TODO implement liveData and Diff contacts.adapters.diff
class UserViewModel : ViewModel() {
    private val _users = ArrayList<User>() //MutableLiveData<List<Contact>>()
    val users: LiveData<List<User>> = _users

    init {
        _users.addAll(LocalUserData().getLocalContactsList())
    }

    fun getUserList(): ArrayList<User> = _users

    fun deleteUser(index: Int) = _users.removeAt(index)
    fun addUser(user: User, position: Int) = _users.add(position, user)
}