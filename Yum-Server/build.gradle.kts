val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val kmongo_version: String by project
val koin_version: String by project
val commons_codec_version: String by project

plugins {
    kotlin("jvm") version "1.9.0"
    id("io.ktor.plugin") version "2.2.4"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
//    `java-library`
//    `maven-publish`
}

group = "com.anbui"
version = "0.0.1"

//publishing {
//    publications {
//        create<MavenPublication>("yumLibrary") {
//            pom {
//                name.set("My Library")
//                description.set("A concise description of my library")
//                url.set("https://www.example.com/library")
//                properties.set(mapOf(
//                    "myProp" to "value",
//                    "prop.with.dots" to "anotherValue"
//                ))
//                licenses {
//                    license {
//                        name.set("The Apache License, Version 2.0")
//                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
//                    }
//                }
//                developers {
//                    developer {
//                        id.set("an ")
//                        name.set("John Doe")
//                        email.set("john.doe@example.com")
//                    }
//                }
//                scm {
//                    connection.set("scm:git:git://example.com/my-library.git")
//                    developerConnection.set("scm:git:ssh://example.com/my-library.git")
//                    url.set("http://example.com/my-library/")
//                }
//            }
//        }
//    }
//
//    repositories {
//        maven {
//            name = "yum"
//            url = uri(layout.buildDirectory.dir("repo"))
//        }
//    }
//}

application {
    mainClass.set("com.anbui.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-core-jvm:2.3.0")
    implementation("io.ktor:ktor-server-sessions-jvm:2.3.0")
    implementation("io.ktor:ktor-server-call-logging-jvm:2.3.0")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:2.3.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:2.3.0")
    implementation("io.ktor:ktor-server-auth-jvm:2.3.0")
    implementation("io.ktor:ktor-server-auth-jwt-jvm:2.3.0")
    implementation("io.ktor:ktor-server-websockets-jvm:2.3.0")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    // KMongo
    implementation("org.litote.kmongo:kmongo:$kmongo_version")
    implementation("org.litote.kmongo:kmongo-coroutine:$kmongo_version")

    // Koin core features
    implementation("io.insert-koin:koin-core:$koin_version")
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:2.3.0")
    testImplementation("io.ktor:ktor-server-test-host-jvm:2.3.0")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.0")

    //codec
//    implementation("commons-codec:commons-codec:$commons_codec_version")
}

tasks.test {
//    ignoreFailures = true
//    filter {
//        excludeTestsMatching("com.anbui.routes.AuthRoutesKtTest.signinAdminOrSomethingTest")
//    }
}


//reporting.baseDir = file("my-reports")
//project.setProperty("testResultsDirName", "$buildDir/my-test-results")
//
//tasks.register("showDirs") {
//    val rootDir = project.rootDir
//    val reportsDir = project.reporting.baseDirectory
//    val testResultsDir = project.java.testResultsDir
//
//    doLast {
//        logger.quiet(rootDir.toPath().relativize(reportsDir.get().asFile.toPath()).toString())
//        logger.quiet(rootDir.toPath().relativize(testResultsDir.get().asFile.toPath()).toString())
//    }
//}
