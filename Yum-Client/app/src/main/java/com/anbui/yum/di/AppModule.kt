package com.anbui.yum.di

//import io.ktor.client.engine.android.*
import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.anbui.yum.data.local.YumDatabase
import com.anbui.yum.data.local.recipe.RecipeEntity
import com.anbui.yum.data.remote.RecipeRemoteMediator
import com.anbui.yum.data.remote.auth.UserService
import com.anbui.yum.data.remote.auth.UserServiceImpl
import com.anbui.yum.data.remote.recipe.RecipeService
import com.anbui.yum.data.remote.recipe.RecipeServiceImpl
import com.anbui.yum.data.remote.review.ReviewService
import com.anbui.yum.data.remote.review.ReviewServiceImpl
import com.anbui.yum.data.remote.user_info.UserInfoService
import com.anbui.yum.data.remote.user_info.UserInfoServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    },
                )
            }

        }
    }

    @Provides
    @Singleton
    fun provideYumDatabase(@ApplicationContext context: Context): YumDatabase {
        return Room.databaseBuilder(
            context,
            YumDatabase::class.java,
            "Yum.db",
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserService(client: HttpClient): UserService {
        return UserServiceImpl(client)
    }

    @Provides
    @Singleton
    fun provideUserInfoService(client: HttpClient): UserInfoService {
        return UserInfoServiceImpl(client)
    }

    @Provides
    @Singleton
    fun provideRecipeService(client: HttpClient): RecipeService {
        return RecipeServiceImpl(client)
    }

    @Provides
    @Singleton
    fun provideReviewService(client: HttpClient): ReviewService {
        return ReviewServiceImpl(client)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideRecipePager(
        yumDb: YumDatabase,
        recipeService: RecipeService,
    ): Pager<Int, RecipeEntity> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            remoteMediator = RecipeRemoteMediator(
                yumDb = yumDb,
                recipeService = recipeService,
            ),
            pagingSourceFactory = {
                yumDb.recipeDao.pagingSource()
            },
        )
    }

//    @Provides
//    @Singleton
//    fun provideChatSocketService(client: HttpClient): ChatSocketService {
//        return ChatSocketServiceImpl(client)
//    }
}
