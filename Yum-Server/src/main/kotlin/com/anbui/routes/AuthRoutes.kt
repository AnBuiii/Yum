package com.anbui.routes

import com.anbui.data.user.User
import com.anbui.data.user.UserDataSource
import com.anbui.data.user_info.UserInfo
import com.anbui.data.user_info.UserInfoDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.userRoute(userDataSource: UserDataSource, userInfoDataSource: UserInfoDataSource) {

    post("signup") {
        val request = call.receive<User>()
        try {
            if (userDataSource.getUserByUsername(request.username) != null) {
                return@post
            }
            userDataSource.insertUser(request)
            val user = userDataSource.getUserByUsername(request.username)
            userInfoDataSource.insertUserInfo(UserInfo(userId = user!!.id))
            call.respond(request.id)
        } catch (e: Exception) {
            application.log.error("fail to sign up")
        }
    }

    post("signin") {
        val request = call.receive<User>()
        try {
            val user = userDataSource.getUserByUsername(request.username) ?: return@post
            if (user.password != request.password) return@post
            call.respond(HttpStatusCode.OK, user.id)
        } catch (e: Exception) {
            application.log.error("Fail to sign in")
        }
    }

    route("user") {
        get {
            try {
                val users = userDataSource.getALlUser()
                call.respond(users)
            } catch (e: Exception) {
                application.log.error("Fail to get all user")
            }
        }
        get("{id}") {
            val id = call.parameters["id"]
            try {
                val user = userDataSource.getUserById(id!!)
                call.respond(user!!)
            } catch (e: Exception) {
                application.log.error("Fail to get all user")
            }
        }
        get("{id}/userInfo") {
            val id = call.parameters["id"]
            try {
                val userInfo = userInfoDataSource.getUserInfoByUserId(id!!)
                call.respond(userInfo!!)
            } catch (e: Exception) {
                application.log.error("Fail to get all user")
            }
        }
    }
}

