package com.anbui.routes

import com.anbui.data.user.User
import com.anbui.plugins.configureRouting
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class AuthRoutesKtTest {

    // black box
//    @Test
//    fun testPostSignin() = testApplication {
//        val client = createClient {
//            install(ContentNegotiation) {
//                json()
//            }
//        }
//
//        application {
//            configureRouting()
//        }
//
//        client.post("/signin") {
//            contentType(ContentType.Application.Json)
//            setBody(
//                mapOf(
//                    "username" to "builehoaian",
//                    "password" to "builehoaian",
//                ),
//            )
//        }.apply {
//            assertEquals(HttpStatusCode.OK, status)
//        }
//    }
//
//    @Test
//    fun signinAdminOrSomethingTest() = testApplication {
//        val client = createClient {
//            install(ContentNegotiation) {
//                json()
//            }
//        }
//
//        application {
//            configureRouting()
//        }
//
//        client.post("/signinAdminOrSomething") {
//            contentType(ContentType.Application.Json)
//            setBody(
//                mapOf(
//                    "username" to "builehoaian",
//                    "password" to "builehoaian",
//                ),
//            )
//        }.apply {
//            assertEquals(HttpStatusCode.OK, status)
//        }
//    }
}
