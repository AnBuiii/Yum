package com.anbui.yum.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfoDto(
    val userId: String = "",
    val name: String = "",
    val title: String = "",
    val id: String = "",
)