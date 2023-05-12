package com.anbui.routes

import com.anbui.data.user_info.UserInfo
import com.anbui.data.user_info.UserInfoDataSource
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.userInfoRoute(userInfoDataSource: UserInfoDataSource) {
    route("/userInfo") {
        // create
        post {
            val request = call.receive<UserInfo>()
            try {
                val userInfo = userInfoDataSource.insertUserInfo(request)
                call.respond(userInfo)
            } catch (e: Exception) {
                application.log.error("Fail to insert new user info")
            }
        }
        get {
            try {
                val userInfos = userInfoDataSource.getAllUserInfo()
                call.respond(userInfos)
            } catch (e: Exception) {
                application.log.error("Fail to get all user info")
            }
        }

        // read
        get("{id}") {
            val id = call.parameters["id"]
            try {
                val userInfo = userInfoDataSource.getUserInfoById(id!!)
                call.respond(userInfo!!)
            } catch (e: Exception) {
                application.log.error("Fail to get user info")
            }
        }

        //update
        put("{id}") {
            val id = call.parameters["id"]
            val request = call.receive<UserInfo>()
            try {
                val userInfo = userInfoDataSource.updateUserInfoById(id!!, request)
                call.respond(userInfo)
            } catch (e: Exception) {
                application.log.error("Fail to get user info")
            }
        }

    }
}

