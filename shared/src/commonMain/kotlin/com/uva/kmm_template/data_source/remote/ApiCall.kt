package com.uva.kmm_template.data_source.remote

import com.uva.kmm_template.data_source.remote.dto.ConversationDto
import com.uva.kmm_template.data_source.remote.dto.MessageDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpMethod

class ApiCall(
    private val httpClient: HttpClient
) {
    suspend fun generateTopics(): String {
        return httpClient.post("chat/completions") {
//            method = HttpMethod.Post
            setBody(
                ConversationDto(
                    model = "gpt-3.5-turbo",
//                    prompt = "generate 4 topics for our conversation"
                    messages = listOf(
                        MessageDto(
                            role = "assistant",
                            content = "generate 4 topics for our conversation"
                        )
                    )
                )
            )
        }.bodyAsText()
    }
}
