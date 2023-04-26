package com.uva.kmm_template.data_source.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ConversationDto(
    val model: String,
    val messages: List<MessageDto>,
    val n: Int,
    val max_tokens: Int
)
