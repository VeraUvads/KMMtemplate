package com.uva.kmm_template.data_source.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionResponse(
    @SerialName("id") var id: String? = null,
    @SerialName("object") var obj: String? = null,
    @SerialName("created") var created: Int? = null,
    @SerialName("model") var model: String? = null,
    @SerialName("usage") var usage: Usage? = Usage(),
    @SerialName("choices") var choices: ArrayList<Choice> = arrayListOf()
)

@Serializable
data class Usage(
    @SerialName("prompt_tokens") var promptTokens: Int? = null,
    @SerialName("completion_tokens") var completionTokens: Int? = null,
    @SerialName("total_tokens") var totalTokens: Int? = null
)

@Serializable
data class Choice(
    @SerialName("message") var message: MessageDto,
    @SerialName("finish_reason") var finishReason: String? = null,
    @SerialName("index") var index: Int? = null
)
