package com.example.recyclerview.data

import com.example.recyclerview.model.User

class LocalUserData {

    fun getLocalContactsList(): List<User> = listOf(
        User("1", "a", "https://cdn-icons-png.flaticon.com/256/10776/10776661.png"),
        User("2", "b", "https://cdn-icons-png.flaticon.com/256/10776/10776607.png"),
        User("3", "c", "https://cdn-icons-png.flaticon.com/256/10776/10776594.png"),
        User("4", "d", "https://cdn-icons-png.flaticon.com/256/10776/10776616.png"),
        User("5", "e", "https://cdn-icons-png.flaticon.com/256/10776/10776580.png")
    )
}