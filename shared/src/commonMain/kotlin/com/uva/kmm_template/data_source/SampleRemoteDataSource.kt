package com.uva.kmm_template.data_source

import com.uva.kmm_template.data_source.remote.ApiCall
import com.uva.kmm_template.data_source.remote.dto.ChatCompletionResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SampleRemoteDataSource(private val apiCall: ApiCall) {
    suspend fun getSampleRemote(): ChatCompletionResponse = apiCall.generateTopics()
    suspend fun askAbout(topic: String): ChatCompletionResponse = apiCall.askAbout(topic)

}
