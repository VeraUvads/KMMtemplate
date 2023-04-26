package com.uva.kmm_template.data_source.remote

import com.uva.kmm_template.data_source.remote.dto.ConversationDto
import com.uva.kmm_template.data_source.remote.dto.MessageDto
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText

class ApiCall(
    private val httpClient: HttpClient
) {
    suspend fun generateTopics(): String {
        val data = ConversationDto(
            model = "gpt-3.5-turbo",
            messages = listOf(
                MessageDto(role = "system", content = "Вы: Привет!"),
                MessageDto(role = "user", content = "Пользователь: Привет, как дела?"),
                MessageDto(role = "assistant", content = "Ассистент:")
            ),
            n = 1,
            max_tokens = 100
        )
        return httpClient.post("v1/chat/completions") {
            setBody(data)
        }.bodyAsText()
    }
}
