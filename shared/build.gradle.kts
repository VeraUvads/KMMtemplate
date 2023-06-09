plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization").version("1.6.21")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.client.serialization)
                implementation(kotlin("stdlib-common"))
                api(libs.kermit)
                api(libs.koin.core)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.okhttp)
                implementation(libs.sqldelight.driver.android)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation(libs.ktor.client.ios)
                implementation(libs.sqldelight.driver.native)
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

android {
    namespace = "com.uva.kmm_template"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
