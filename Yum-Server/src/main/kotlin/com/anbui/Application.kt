package com.anbui

import com.anbui.data.user.UserDatasourceImpl
import com.anbui.di.userModule
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.anbui.plugins.*
import com.anbui.security.token.TokenConfig
import org.koin.ktor.plugin.Koin
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    install(Koin) {
        modules(userModule)
    }

    configureSecurity()
    configureRouting()
    configureMonitoring()
    configureSerialization()
//    configureSockets()

}
