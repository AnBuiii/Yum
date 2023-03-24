package com.example.yum.data.remote.dto

import com.example.yum.domain.model.UserInfo
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoDto(
    val userId: String,
    val name: String,
    val title: String,
    val id: String,
) {
    fun toUserInfo(): UserInfo {
        return UserInfo(
            userId = userId,
            name = name,
            title = title
        )
    }
}