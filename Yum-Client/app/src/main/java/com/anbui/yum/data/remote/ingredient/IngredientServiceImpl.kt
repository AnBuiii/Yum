package com.anbui.yum.data.remote.ingredient

import com.anbui.yum.common.util.Constants.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class IngredientServiceImpl(
    private val client: HttpClient,
) : IngredientService {
    override suspend fun search(query: String): List<IngredientDto> {
        return try {
            val respond = client.get("${BASE_URL}/ingredient/search") {
                url {
                    parameters.append(
                        "q",
                        query,
                    )
                }
            }.body<List<IngredientDto>>()
            respond
        } catch (e: Exception) {
            listOf(
                IngredientDto(name = query),
            )
        }
    }

    override suspend fun getIngredientById(id: String): IngredientDto {
        return try {
            val respond = client.get("${BASE_URL}/ingredient/$id").body<IngredientDto>()
            respond
        } catch (e: Exception) {
            IngredientDto(
                id = id,
                name = "Unknown",
            )
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
