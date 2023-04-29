package com.uva.kmm_template.repository

import com.uva.kmm_template.data_source.SampleRemoteDataSource
import com.uva.kmm_template.data_source.remote.dto.ChatCompletionResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SampleRepository(private val remoteDataSource: SampleRemoteDataSource) {
    suspend fun getSampleRemote(): ChatCompletionResponse {
        return remoteDataSource.getSampleRemote()
    }

//    suspend fun getSampleLocal(): Flow<Any> {
//        return sampleDataSource.getSampleLocal()
//    }
}
