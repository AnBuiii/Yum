package com.example.yum.data.remote.dto

import com.example.yum.domain.model.User
import kotlinx.serialization.Serializable


@Serializable
data class UserDto(
    val username: String,
    val password: String,
    val id: String
){
    fun toUser() :User{
        return User(username, password)
    }
}
