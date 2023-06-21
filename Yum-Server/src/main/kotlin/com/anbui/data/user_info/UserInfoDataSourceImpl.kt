package com.anbui.data.user_info

import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class UserInfoDataSourceImpl(db: CoroutineDatabase) : UserInfoDataSource {
    private val userInfos = db.getCollection<UserInfo>()
    override suspend fun getUserInfoById(id: String): UserInfo? {
        return userInfos.findOne(UserInfo::id eq id)
    }

    override suspend fun insertUserInfo(userInfo: UserInfo): Boolean {
        return userInfos.insertOne(userInfo).wasAcknowledged()
    }

    override suspend fun updateUserInfoById(id: String, userInfo: UserInfo): Boolean {
        return userInfos.replaceOneById(id, userInfo).wasAcknowledged()
    }

    override suspend fun getUserInfoByUserId(id: String): UserInfo? {
        return userInfos.findOne(UserInfo::userId eq id)
    }

    override suspend fun getAllUserInfo(): List<UserInfo> {
        return userInfos.find().toList()
    }
}
