package com.uva.kmm_template.android.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.android.BuildConfig
import java.util.concurrent.Executors
import co.touchlab.kermit.Logger as Kermit

abstract class ViewState : Any()
abstract class ViewEvent : Any()
abstract class ViewAction : Any()

abstract class StateViewModel<State : ViewState, Action : ViewAction, Event : ViewEvent>(
    initialState: State
) : ViewModel() {

    private val queueDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    private val queueScope = CoroutineScope(queueDispatcher + SupervisorJob())

    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state

    private val _event: Channel<Event> = Channel(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Kermit.e(exception.message.orEmpty())
        onError(exception)
    }

    /**
     * Обновление State
     */
    protected fun reduce(block: State.() -> State) {
        queueScope.launch(exceptionHandler) { _state.value = block(state.value) }
    }

    /**
     * Для отправки Single Live Event на UI
     */
    protected fun onEvent(vararg event: Event) {
        viewModelScope.launch(exceptionHandler) {
            event.forEach { event -> _event.send(event) }
        }
    }

    /**
     * Этот метод дергаем со стороны UI если хочеца одно место обработки Action
     */
    fun onReceiveAction(action: Action) {
        checkMainThread()
        queueScope.launch(exceptionHandler) { onAction(action) }
    }

    protected open fun onAction(action: Action) = Unit

    protected open fun onError(error: Throwable) {
        if (BuildConfig.DEBUG) throw error
    }

    override fun onCleared() {
        super.onCleared()
        queueScope.cancel()
    }
}
