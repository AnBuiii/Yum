package com.anbui.yum.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.anbui.yum.data.local.YumDatabase
import com.anbui.yum.data.remote.RecipeRemoteMediator
import com.anbui.yum.data.remote.auth.UserService
import com.anbui.yum.data.remote.auth.UserServiceImpl
import com.anbui.yum.data.remote.collection.CollectionService
import com.anbui.yum.data.remote.collection.CollectionServiceImpl
import com.anbui.yum.data.remote.ingredient.IngredientService
import com.anbui.yum.data.remote.ingredient.IngredientServiceImpl
import com.anbui.yum.data.remote.recipe.RecipeService
import com.anbui.yum.data.remote.recipe.RecipeServiceImpl
import com.anbui.yum.data.remote.review.ReviewService
import com.anbui.yum.data.remote.review.ReviewServiceImpl
import com.anbui.yum.data.remote.shopping_list.ShoppingService
import com.anbui.yum.data.remote.shopping_list.ShoppingServiceImpl
import com.anbui.yum.data.remote.user_info.UserInfoService
import com.anbui.yum.data.remote.user_info.UserInfoServiceImpl
import com.anbui.yum.presentation.cart.CartViewModel
import com.anbui.yum.presentation.collection.CollectionViewModel
import com.anbui.yum.presentation.feed.FeedViewModel
import com.anbui.yum.presentation.other_user.OtherUserViewModel
import com.anbui.yum.presentation.recipe.RecipeDetailViewModel
import com.anbui.yum.presentation.review.ReviewViewModel
import com.anbui.yum.presentation.search.SearchViewModel
import com.anbui.yum.presentation.sign_in.SignInViewModel
import com.anbui.yum.presentation.sign_up.SignUpViewModel
import com.anbui.yum.presentation.splash.SplashViewModel
import com.anbui.yum.presentation.user.UserViewModel
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


@OptIn(ExperimentalPagingApi::class)
val appModule = module {
    single {
        HttpClient(CIO) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(
//                    contentType(ContentType.Application.Json)
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    },
                )
            }
        }
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            YumDatabase::class.java,
            "Yum.db",
        ).build()
    }

    single {
        val yumDb: YumDatabase = get()
        Pager(
            config = PagingConfig(pageSize = 5),
            remoteMediator = RecipeRemoteMediator(
                yumDb = yumDb,
                recipeService = get(),
            ),
            pagingSourceFactory = {
                yumDb.recipeDao.pagingSource()
            },
        )
    }

    singleOf(::UserServiceImpl) { bind<UserService>() }

    singleOf(::UserInfoServiceImpl) { bind<UserInfoService>() }

    singleOf(::RecipeServiceImpl) { bind<RecipeService>() }

    singleOf(::ReviewServiceImpl) { bind<ReviewService>() }

    singleOf(::IngredientServiceImpl) { bind<IngredientService>() }

    singleOf(::ShoppingServiceImpl) { bind<ShoppingService>() }

    singleOf(::CollectionServiceImpl) { bind<CollectionService>() }

    viewModelOf(::CartViewModel)

    viewModelOf(::FeedViewModel)

    viewModelOf(::RecipeDetailViewModel)

    viewModelOf(::SearchViewModel)

    viewModelOf(::SignInViewModel)

    viewModelOf(::SignUpViewModel)

    viewModelOf(::SplashViewModel)

    viewModelOf(::UserViewModel)

    viewModelOf(::OtherUserViewModel)

    viewModelOf(::ReviewViewModel)

    viewModelOf(::CollectionViewModel)

}


//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {

//    @Provides
//    @Singleton
//    fun provideHttpClient(): HttpClient {
//        return HttpClient(CIO) {
//            install(Logging) {
//                level = LogLevel.ALL
//            }
//            install(ContentNegotiation) {
//                json(
//                    Json {
//                        prettyPrint = true
//                        isLenient = true
//                        ignoreUnknownKeys = true
//                    },
//                )
//            }
//
//        }
//    }
//
//    @Provides
//    @Singleton
//    fun provideYumDatabase(@ApplicationContext context: Context): YumDatabase {
//        return Room.databaseBuilder(
//            context,
//            YumDatabase::class.java,
//            "Yum.db",
//        ).build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideUserService(client: HttpClient): UserService {
//        return UserServiceImpl(client)
//    }
//
//    @Provides
//    @Singleton
//    fun provideUserInfoService(client: HttpClient): UserInfoService {
//        return UserInfoServiceImpl(client)
//    }
//
//    @Provides
//    @Singleton
//    fun provideRecipeService(client: HttpClient): RecipeService {
//        return RecipeServiceImpl(client)
//    }
//
//    @Provides
//    @Singleton
//    fun provideReviewService(client: HttpClient): ReviewService {
//        return ReviewServiceImpl(client)
//    }
//
//    @Provides
//    @Singleton
//    fun provideIngredientService(client: HttpClient): IngredientService {
//        return IngredientServiceImpl(client)
//    }
//
//    @OptIn(ExperimentalPagingApi::class)
//    @Provides
//    @Singleton
//    fun provideRecipePager(
//        yumDb: YumDatabase,
//        recipeService: RecipeService,
//    ): Pager<Int, RecipeEntity> {
//        return Pager(
//            config = PagingConfig(pageSize = 5),
//            remoteMediator = RecipeRemoteMediator(
//                yumDb = yumDb,
//                recipeService = recipeService,
//            ),
//            pagingSourceFactory = {
//                yumDb.recipeDao.pagingSource()
//            },
//        )
//    }


//    @Provides
//    @Singleton
//    fun provideChatSocketService(client: HttpClient): ChatSocketService {
//        return ChatSocketServiceImpl(client)
//    }
//}
