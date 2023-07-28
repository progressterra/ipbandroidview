package com.progressterra.ipbandroidview.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel<STATE : Any, EFFECT : Any>(initialState: STATE) : ViewModel() {

    private val _state: MutableState<STATE> = mutableStateOf(initialState)
    val state: State<STATE> = _state


    private val _effects: Channel<EFFECT> = Channel()

    @Suppress("ComposableNaming")
    @Composable
    fun collectEffects(
        lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
        minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
        action: suspend (EFFECT) -> Unit
    ) {
        LaunchedEffect(Unit) {
            lifecycleOwner.lifecycleScope.launch {
                _effects.receiveAsFlow().flowWithLifecycle(lifecycleOwner.lifecycle, minActiveState)
                    .collect(action)
            }
        }
    }

    protected fun onBackground(
        block: suspend () -> Unit
    ) {
        viewModelScope.launch { block() }
    }

    protected fun fastEmitState(reducer: (STATE) -> STATE) {
        val newState = reducer(state.value)
        _state.value = newState
    }

    protected suspend fun emitState(reducer: (STATE) -> STATE) {
        withContext(Dispatchers.Main) {
            val newState = reducer(state.value)
            _state.value = newState
        }
    }

    protected fun postEffect(effect: EFFECT) {
        viewModelScope.launch { _effects.send(effect) }
    }

    protected fun <T : Any> cachePaging(toBeCached: Flow<PagingData<T>>): Flow<PagingData<T>> =
        toBeCached.cachedIn(viewModelScope)
}