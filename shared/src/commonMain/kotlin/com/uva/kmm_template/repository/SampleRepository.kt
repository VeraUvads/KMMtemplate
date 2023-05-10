package com.uva.kmm_template.repository

import com.uva.kmm_template.data_source.SampleRemoteDataSource
import com.uva.kmm_template.data_source.remote.dto.ChatCompletionResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SampleRepository() : KoinComponent {

    private val remoteDataSource: SampleRemoteDataSource by inject()
    suspend fun getSampleRemote(): ChatCompletionResponse {
        return remoteDataSource.getSampleRemote()
    }

    //    suspend fun getSampleLocal(): Flow<Any> {
    //        return sampleDataSource.getSampleLocal()
    //    }
}
