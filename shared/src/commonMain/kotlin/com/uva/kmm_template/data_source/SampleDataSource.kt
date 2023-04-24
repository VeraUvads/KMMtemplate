package com.uva.kmm_template.data_source

import com.uva.kmm_template.data_source.remote.ApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SampleDataSource(private val apiCall: ApiCall) {
    suspend fun getSampleRemote(): String = apiCall.generateTopics()

    suspend fun getSampleLocal(): Flow<Any> {
        return flow {
            emit(Unit)
        }
    }
}
