package com.uva.kmm_template.data_source.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MessageDto(
    val role: String,
    val content: String
)
