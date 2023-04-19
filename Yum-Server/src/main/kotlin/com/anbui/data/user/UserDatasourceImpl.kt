package com.anbui.data.user

import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class UserDatasourceImpl(
    db: CoroutineDatabase
): UserDataSource {

    private val users = db.getCollection<User>()
    override suspend fun getUserByUsername(username: String): User? {
        return users.findOne(User::username eq username)
    }

    override suspend fun getUserById(id: String): User? {
        return users.findOne(User::id eq id)
    }

    override suspend fun insertUser(user: User): Boolean {
        return users.insertOne(user).wasAcknowledged()
    }

    override suspend fun getALlUser(): List<User> {
        return users.find().toList()
    }

}