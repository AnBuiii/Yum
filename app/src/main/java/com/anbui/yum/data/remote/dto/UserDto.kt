package com.anbui.yum.data.remote.dto

import com.anbui.yum.domain.model.User
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
