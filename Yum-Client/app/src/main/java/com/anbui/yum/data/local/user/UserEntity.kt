package com.anbui.yum.data.local.user

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserEntity(
    @PrimaryKey val userId: String
)

