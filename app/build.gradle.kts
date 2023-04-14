@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("android")
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.serialization)
    kotlin("kapt")
}
android {
    namespace = "com.anbui.yum"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.anbui.yum"
        minSdk = 33
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.4"
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ktx)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.util)
//    implementation(libs.androidx.compose.ui.unit)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.testManifest)
    androidTestImplementation(libs.androidx.compose.ui.test)
    implementation(libs.androidx.compose.material3)
//    implementation(libs.androidx.compose.material)

    //test
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)

    implementation(libs.accompanist.systemuicontroller)

    //splash screen
    implementation(libs.androidx.splash.screen)

    //navigation
    implementation(libs.androidx.navigation.compose)

    //dagger - hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    //coroutine
    implementation(libs.kotlinx.coroutines.android)

    //ktor
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
//    implementation("ch.qos.logback:logback-classic:1.3.0")

    // room
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)

    // coil
    implementation(libs.coil.kt.compose)





}
