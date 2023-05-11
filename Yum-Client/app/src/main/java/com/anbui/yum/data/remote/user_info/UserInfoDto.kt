package com.anbui.yum.data.remote.user_info

import kotlinx.serialization.Serializable

@Serializable
data class UserInfoDto(
    val userId: String = "",
    val name: String = "",
    val title: String = "",
    val imageUrl: String = "",
    val id: String = "",
)
