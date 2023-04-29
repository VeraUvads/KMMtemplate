package com.uva.kmm_template.data_source.remote

import com.uva.kmm_template.data_source.remote.dto.ChatCompletionResponse
import com.uva.kmm_template.data_source.remote.dto.ConversationDto
import com.uva.kmm_template.data_source.remote.dto.MessageDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class ApiCall(
    private val httpClient: HttpClient
) {
    suspend fun generateTopics(): ChatCompletionResponse {
        val data = ConversationDto(
            model = "gpt-3.5-turbo",
            messages = listOf(
                MessageDto(role = "user", content = "Сгенерируй через запятую 4 темы для разговора"),
            ),
            n = 1,
            max_tokens = 100
        )

        return httpClient.post("v1/chat/completions") {
            setBody(data)
        }.body()
    }
}
