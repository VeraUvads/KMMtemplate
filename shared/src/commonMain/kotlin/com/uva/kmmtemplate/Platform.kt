package com.uva.kmmtemplate

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform