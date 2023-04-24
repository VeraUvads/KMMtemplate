package com.uva.kmm_template.data_source.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpSendPipeline
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpClientProvider(private val endpoint: Endpoint) {
    operator fun invoke(): HttpClient {
        val client = HttpClient() {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
            defaultRequest {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                url(endpoint.url)
            }
        }
        client.sendPipeline.intercept(HttpSendPipeline.State) {
            (this.toString())
            context.headers.append("Authorization", "Bearer $OPEN_AI_API_KEY")
        }
        return client
    }
}
