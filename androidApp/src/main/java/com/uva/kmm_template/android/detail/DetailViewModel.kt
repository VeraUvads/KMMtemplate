package com.uva.kmm_template.android.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.uva.kmm_template.android.navigation.arg
import com.uva.kmm_template.android.utils.StateViewModel
import com.uva.kmm_template.repository.SampleRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class DetailViewModel(
    private val sampleRepository: SampleRepository,
    private val savedStateHandle: SavedStateHandle
) : StateViewModel<DetailComponents.State, DetailComponents.Action, DetailComponents.Event>(
    initialState = DetailComponents.State()
) {
    private val detailedInfo by savedStateHandle.arg<String>()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, _ ->
                reduce { copy(isLoading = false, isError = true) }
            }
        ) {
            val response = sampleRepository.askAbout(detailedInfo).choices.first().message.content
            reduce { copy(isLoading = false, isError = false, data = response) }
        }
    }
}
