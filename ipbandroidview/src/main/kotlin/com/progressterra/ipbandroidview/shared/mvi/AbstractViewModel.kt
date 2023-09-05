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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class AbstractViewModel<S : Any, E : Any> : ViewModel(), Operations {

    private val initialState: S by lazy { createInitialState() }

    abstract fun createInitialState(): S

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    val currentState: S
        get() = state.value

    private val _effects: MutableSharedFlow<E> = MutableSharedFlow()

    @Suppress("ComposableNaming")
    @Composable
    fun collectEffects(
        lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
        minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
        action: suspend (E) -> Unit
    ) {
        LaunchedEffect(Unit) {
            lifecycleOwner.lifecycleScope.launch {
                _effects.flowWithLifecycle(lifecycleOwner.lifecycle, minActiveState)
                    .collect(action)
            }
        }
    }

    override fun onBackground(
        block: suspend () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) { block() }
    }

    protected fun emitState(reducer: (S) -> S) {
        val newState = reducer(currentState)
        _state.value = newState
    }

    protected fun postEffect(effect: E) {
        _effects.tryEmit(effect)
    }

    override fun <T : Any> cachePaging(toBeCached: Flow<PagingData<T>>): Flow<PagingData<T>> =
        toBeCached.cachedIn(viewModelScope)
}