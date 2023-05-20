package com.anbui.di

import com.anbui.data.collection.Collection
import com.anbui.data.collection.CollectionDataSource
import com.anbui.data.collection.CollectionDataSourceImpl
import com.anbui.data.ingredient.IngredientDataSource
import com.anbui.data.ingredient.IngredientDataSourceImpl
import com.anbui.data.recipe.RecipeDataSource
import com.anbui.data.recipe.RecipeDataSourceImpl
import com.anbui.data.review.ReviewDataSource
import com.anbui.data.review.ReviewDataSourceImpl
import com.anbui.data.shoping_list.ShoppingItemDataSource
import com.anbui.data.shoping_list.ShoppingItemDataSourceImpl
import com.anbui.data.user.UserDataSource
import com.anbui.data.user.UserDatasourceImpl
import com.anbui.data.user_info.UserInfoDataSource
import com.anbui.data.user_info.UserInfoDataSourceImpl
import com.anbui.security.token.JwtTokenImpl
import com.anbui.security.token.TokenService
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val userModule = module {
    single {
        val mongoPw = System.getenv("MONGO_PW")
        val dbName = "yum-db"
        KMongo.createClient(
//            connectionString = "mongodb+srv://builehoaian:builehoaian@cluster0.kwm3mj1.mongodb.net/$dbName?retryWrites=true&w=majority",
            connectionString = "mongodb://localhost:27017/$dbName?retryWrites=true&w=majority",

            ).coroutine
            .getDatabase(dbName)

    }

    singleOf(::ShoppingItemDataSourceImpl) { bind<ShoppingItemDataSource>() }

    single<UserDataSource> {
        UserDatasourceImpl(get())
    }
    single<UserInfoDataSource> {
        UserInfoDataSourceImpl(get())
    }
    single<RecipeDataSource> {
        RecipeDataSourceImpl(get())
    }
    single<IngredientDataSource> {
        IngredientDataSourceImpl(get())
    }
    single<ReviewDataSource> {
        ReviewDataSourceImpl(get())
    }
    single<TokenService> {
        JwtTokenImpl()
    }
    single<CollectionDataSource> {
        CollectionDataSourceImpl(get())
    }
}
