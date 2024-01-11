package com.progressterra.ipbandroidview.shared.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Abstract class for viewmodels. Made on MVI pattern. There are state flow and effects flow (implemented used channels). Has a method to collect effects in composable functions.
 */
abstract class AbstractViewModel<S : Any, E : Any> : ViewModel(), Operations {

    private val initialState: S by lazy { createInitialState() }

    abstract fun createInitialState(): S

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    val currentState: S
        get() = state.value

    private val _effects: Channel<E> = Channel()

    @Suppress("ComposableNaming")
    @Composable
    fun collectEffects(
        lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
        minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
        action: suspend (E) -> Unit
    ) {
        LaunchedEffect(Unit) {
            lifecycleOwner.lifecycleScope.launch {
                _effects.receiveAsFlow().flowWithLifecycle(lifecycleOwner.lifecycle, minActiveState)
                    .collect(action)
            }
        }
    }

    override fun onBackground(
        block: suspend () -> Unit
    ) : Job = viewModelScope.launch(Dispatchers.Default) { block() }

    protected fun emitState(reducer: (S) -> S) {
        _state.update { reducer(it) }
    }

    protected fun postEffect(effect: E) {
        viewModelScope.launch { _effects.send(effect) }
    }

    override fun <T : Any> cachePaging(toBeCached: Flow<PagingData<T>>): Flow<PagingData<T>> =
        toBeCached.cachedIn(viewModelScope)
}