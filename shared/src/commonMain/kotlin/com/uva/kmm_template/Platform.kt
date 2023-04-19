package com.uva.kmm_template

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform