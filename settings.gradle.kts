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
            library("kotlinx-coroutines-core", "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
            library("touchlab-kermit", "co.touchlab:kermit:1.2.2")
            library("ktor-client-core", "io.ktor:ktor-client-core:2.2.4")
            library("ktor-client-ios", "io.ktor:ktor-client-ios:2.2.4")
            library("ktor-client-okhttp", "io.ktor:ktor-client-okhttp:2.2.4")
            library("sqldelight-driver-native", "com.squareup.sqldelight:native-driver:1.5.5")
            library("sqldelight-driver-android", "com.squareup.sqldelight:android-driver:1.5.5")
        }
    }
}

rootProject.name = "KMM_template"
include(":androidApp")
include(":shared")