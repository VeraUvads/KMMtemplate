package com.uva.kmm_template.android.home

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uva.kmm_template.repository.SampleRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val sampleRepository: SampleRepository) : ViewModel() {

    private val _state = MutableStateFlow<HomeComponents.State>(HomeComponents.State.Loading)
    val state: StateFlow<HomeComponents.State> = _state
    val regex = "[0-9]+"

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, exception ->
                _state.value = HomeComponents.State.Error(exception.message)
            }
        ) {
            val items =
                sampleRepository.getSampleRemote().choices.first().message.content
                    .replace(regex.toRegex(), "").replace(". ", "")
                    .split("\n")
            _state.value = HomeComponents.State.Success(items)
        }
    }
}
