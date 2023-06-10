package com.example.recyclerview.repository

import com.example.recyclerview.domain.dataclass.User

interface IContactsRecyclerViewListener {

    fun delete(user: User)

}