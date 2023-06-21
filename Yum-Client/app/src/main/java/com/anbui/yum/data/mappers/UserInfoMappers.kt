package com.anbui.yum.data.mappers

import com.anbui.yum.data.remote.user_info.UserInfoDto
import com.anbui.yum.domain.model.UserInfo

fun UserInfoDto.toUserInfo(): UserInfo {
    return UserInfo(
        userId = userId,
        name = name,
        title = title,
        imageUrl = imageUrl,
        id = id,
    )
}

fun UserInfo.toUserInfoDto(): UserInfoDto{
    return UserInfoDto(
        userId = userId,
        name = name,
        title = title,
        imageUrl = imageUrl,
        id = id,
    )
}
