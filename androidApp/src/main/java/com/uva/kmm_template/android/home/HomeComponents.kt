package com.uva.kmm_template.android.home

object HomeComponents {

    sealed class State {
        object Loading : State()
        data class Success(val items: List<String>) : State()
        data class Error(val message: String?) : State()
    }

    class Event
    class Action
}
