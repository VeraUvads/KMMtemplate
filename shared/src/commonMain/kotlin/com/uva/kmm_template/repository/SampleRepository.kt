package com.uva.kmm_template.repository

import com.uva.kmm_template.data_source.SampleDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SampleRepository(private val sampleDataSource: SampleDataSource) {
    fun getSampleRemote(): Flow<String> {
        return flow {
            emit(sampleDataSource.getSampleRemote())
        }
    }

    suspend fun getSampleLocal(): Flow<Any> {
        return sampleDataSource.getSampleLocal()
    }
}
