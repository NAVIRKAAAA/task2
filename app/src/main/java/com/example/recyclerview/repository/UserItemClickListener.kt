package com.example.recyclerview.repository

import com.example.recyclerview.domain.model.localdataset.model.User

interface UserItemClickListener {
    fun onUserDelete(user: User, position: Int)
}
