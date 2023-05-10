package com.anbui.yum.data.remote.ingredient

import com.anbui.yum.common.util.Constants.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class IngredientServiceImpl(
    private val client: HttpClient,
) : IngredientService {
    override suspend fun search(query: String): List<String> {
        return try {
            val respond = client.get("${BASE_URL}/ingredient/search") {
                url {
                    parameters.append(
                        "q",
                        query,
                    )
                }
            }.body<List<String>>()
            respond
        } catch (e: Exception) {
            suggestions
        }
    }

}

private val suggestions = listOf(
    "egg",
    "flour",
    "corn",
    "milk",
    "water",
    "sugar",
    "salt",
    "rice",
    "cream",
    "ab",
)
