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
            library(
                "kotlinx-coroutines-core",
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
            )
            library("touchlab-kermit", "co.touchlab:kermit:1.2.2")

            val ktorVersion = "2.2.4"
            library("ktor-client-core", "io.ktor:ktor-client-core:$ktorVersion")
            library("ktor-client-ios", "io.ktor:ktor-client-ios:$ktorVersion")
            library("ktor-client-okhttp", "io.ktor:ktor-client-okhttp:$ktorVersion")

            val sqlDelVersion = "1.5.5"
            library(
                "sqldelight-driver-native",
                "com.squareup.sqldelight:native-driver:$sqlDelVersion"
            )
            library(
                "sqldelight-driver-android",
                "com.squareup.sqldelight:android-driver:$sqlDelVersion"
            )

            val version = "3.2.0"
            library("koin-core", "io.insert-koin:koin-core:$version")
            library("koin-android", "io.insert-koin:koin-android:$version")
        }
    }
}

rootProject.name = "KMM template"
include(":androidApp")
include(":shared")
