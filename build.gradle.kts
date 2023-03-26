buildscript{
    repositories{
        google()
        mavenCentral()
    }
    dependencies{
        classpath("com.android.tools.build:gradle:7.4.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
        classpath ("org.jetbrains.kotlin:kotlin-serialization:1.7.0")
    }
}
plugins {
    id("com.google.dagger.hilt.android") version "2.44" apply false
}
tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}