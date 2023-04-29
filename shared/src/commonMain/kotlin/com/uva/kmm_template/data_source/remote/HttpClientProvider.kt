package com.uva.kmm_template.data_source.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.call.receive
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpSendPipeline
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSecure
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import co.touchlab.kermit.Logger as Kermit

class HttpClientProvider(private val endpoint: Endpoint) {
    operator fun invoke(): HttpClient {
        val client = HttpClient() {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Kermit.i(message)
                    }
                }
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
            HttpResponseValidator {
                validateResponse { response ->
                    if (response.status != HttpStatusCode.OK) {
                        val errorBody = response.body<String>()
                        error("Произошла ошибка при отправке запроса: $errorBody")
                    }
                }
            }
        }

        client.sendPipeline.intercept(HttpSendPipeline.State) {
            (this.toString())
            context.headers.append("Authorization", "Bearer $OPEN_AI_API_KEY")
        }
        return client
    }
}
