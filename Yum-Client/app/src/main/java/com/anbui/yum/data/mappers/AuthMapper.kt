package com.anbui.yum.data.mappers

import com.anbui.yum.data.local.user.UserEntity
import com.anbui.yum.data.remote.auth.AuthRespondDto

fun AuthRespondDto.toUserEntity(): UserEntity{
    return UserEntity(
        userId = userId
    )
}
