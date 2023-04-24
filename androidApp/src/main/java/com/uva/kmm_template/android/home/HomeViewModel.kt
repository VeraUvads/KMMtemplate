package com.uva.kmm_template.android.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uva.kmm_template.repository.SampleRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val sampleRepository: SampleRepository) : ViewModel() {

    private val someRemoteData = sampleRepository.getSampleRemote()
        .catch { Log.i("sdsdsd", ": $it") }
//    private val someLocalData = sampleRepository.getSampleLocal()

    val state = combine(
        someRemoteData
//        someLocalData
    ) { local ->
        HomeComponents.State(
            isLoading = false,
            data = local[0]
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeComponents.State())

    fun start() {
        viewModelScope.launch {
            someRemoteData.collect()
        }
    }

    init {
    }
}
