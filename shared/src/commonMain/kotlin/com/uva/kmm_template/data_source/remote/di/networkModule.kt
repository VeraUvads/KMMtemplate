package com.uva.kmm_template.data_source.remote.di

import com.uva.kmm_template.data_source.remote.ApiCall
import com.uva.kmm_template.data_source.remote.Endpoint
import com.uva.kmm_template.data_source.remote.Endpoints
import com.uva.kmm_template.data_source.remote.HttpClientProvider
import io.ktor.client.HttpClient
import org.koin.dsl.module

val networkModule = module {
    single<Endpoint> { Endpoints.DEV }
    single<HttpClientProvider> { HttpClientProvider(endpoint = get()) }
    single<HttpClient> { get<HttpClientProvider>().invoke() }
    single<ApiCall> { ApiCall(httpClient = get()) }
}
