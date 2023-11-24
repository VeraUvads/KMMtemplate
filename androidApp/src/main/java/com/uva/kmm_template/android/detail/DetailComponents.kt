package com.uva.kmm_template.android.detail

import com.uva.kmm_template.android.utils.ViewAction
import com.uva.kmm_template.android.utils.ViewEvent
import com.uva.kmm_template.android.utils.ViewState

object DetailComponents {

    data class State( // todo лучше как state isError isLoading
        val isLoading: Boolean = true,
        val data: String = "",
        val isError: Boolean = false
    ) : ViewState()

    class Event : ViewEvent()
    sealed class Action : ViewAction()
}