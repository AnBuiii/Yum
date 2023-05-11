@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
//    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
}
tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}
true
