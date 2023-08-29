package com.progressterra.ipbandroidview.shared.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node


abstract class AbstractNode<INPUT : Any, NAVIGATION : Any, STATE : Any, EFFECT : Any>(
    buildContext: BuildContext,
    private val input: INPUT,
    private val navigation: NAVIGATION
) : Node(buildContext) {

    protected fun onEachOpen() = Unit

    protected fun mapEffect(effect: EFFECT) = Unit

    @Composable
    abstract fun obtainViewModel(): AbstractViewModel<STATE, EFFECT>

    @Composable
    abstract fun Screen(
        modifier: Modifier, state: STATE, viewModel: AbstractViewModel<STATE, EFFECT>
    )

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = obtainViewModel()
        viewModel.collectEffects(
            lifecycleOwner = LocalLifecycleOwner.current,
            minActiveState = Lifecycle.State.STARTED
        ) { mapEffect(it) }
        LaunchedEffect(Unit) {
            onEachOpen()
        }
        val state = viewModel.state.collectAsState().value
        Screen(modifier = modifier, state = state, viewModel = viewModel)
    }
}

