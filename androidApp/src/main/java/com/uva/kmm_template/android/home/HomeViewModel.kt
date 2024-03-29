package com.uva.kmm_template.android.home

import androidx.lifecycle.viewModelScope
import com.uva.kmm_template.android.detail.DetailDestination
import com.uva.kmm_template.android.navigation.DestinationRule
import com.uva.kmm_template.android.navigation.with
import com.uva.kmm_template.android.utils.StateViewModel
import com.uva.kmm_template.repository.SampleRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class HomeViewModel(private val sampleRepository: SampleRepository) :
    StateViewModel<HomeComponents.State, HomeComponents.Action, HomeComponents.Event>(
        initialState = HomeComponents.State()
    ) {

    private val regex = "[0-9]+"

    init {
        loadData()
    }

    override fun onAction(action: HomeComponents.Action) {
        when (action) {
            is HomeComponents.Action.OnItemClick -> {
                sendEvent(HomeComponents.Event.NavigateTo(DetailDestination.with(action.item))) // todo
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, _ ->
                reduce { copy(isLoading = false, isError = true) }
            }
        ) {
            val items = sampleRepository.getSampleRemote().choices.first().message.content
                .replace(regex.toRegex(), "")
                .replace(". ", "")
                .replace(") ", "")
                .split("\n")
            reduce { copy(isLoading = false, isError = false, items = items) }
        }
    }
}
