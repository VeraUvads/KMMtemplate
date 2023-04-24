pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            val coroutinesVersion = "1.6.4"
            library(
                "kotlinx-coroutines-core",
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
            )
            library(
                "kotlinx-coroutines-android",
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
            )

            val ktorVersion = "2.3.0"
            library("ktor-client-core", "io.ktor:ktor-client-core:$ktorVersion")
            library("ktor-client-ios", "io.ktor:ktor-client-darwin:$ktorVersion")
            library("ktor-client-okhttp", "io.ktor:ktor-client-okhttp:$ktorVersion")
            library("ktor-client-logging", "io.ktor:ktor-client-logging:$ktorVersion")
            library("ktor-client-content-negotiation", "io.ktor:ktor-client-content-negotiation:$ktorVersion")
            library("ktor-client-serialization", "io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

            val sqlDelVersion = "1.5.5"
            library(
                "sqldelight-driver-native",
                "com.squareup.sqldelight:native-driver:$sqlDelVersion"
            )
            library(
                "sqldelight-driver-android",
                "com.squareup.sqldelight:android-driver:$sqlDelVersion"
            )

            val koinVersion = "3.2.0"
            library("koin-core", "io.insert-koin:koin-core:$koinVersion")
            library("koin-android", "io.insert-koin:koin-android:$koinVersion")
            library("koin-compose", "io.insert-koin:koin-androidx-compose:$koinVersion")
        }
    }
}

rootProject.name = "KMM template"
include(":androidApp")
include(":shared")
