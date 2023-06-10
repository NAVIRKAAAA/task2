package com.example.recyclerview.domain.dataclass

import java.util.UUID


data class User(
    val name: String,
    val career: String,
    val photo: String = "", // or val photo: String? = null
    val id: UUID = UUID.randomUUID()
)
