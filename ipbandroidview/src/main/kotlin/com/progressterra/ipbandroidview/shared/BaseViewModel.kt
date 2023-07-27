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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

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
        viewModelScope.launch(Dispatchers.IO) { block() }
    }

    protected fun emitState(reducer: (STATE) -> STATE) {
        viewModelScope.launch(Dispatchers.Main) {
            val newState = reducer(state.value)
            _state.value = newState
        }
    }

    protected fun postEffect(effect: EFFECT) {
        viewModelScope.launch(Dispatchers.IO) { _effects.send(effect) }
    }
}