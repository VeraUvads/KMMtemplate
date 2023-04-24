package com.uva.kmm_template.data_source.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ConversationDto(
    val model: String ,
//    val prompt: String

    val messages: List<MessageDto>
)