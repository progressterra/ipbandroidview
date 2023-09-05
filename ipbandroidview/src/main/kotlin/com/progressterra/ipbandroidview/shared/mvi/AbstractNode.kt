package com.progressterra.ipbandroidview.shared.mvi

import android.content.Context
import androidx.annotation.CallSuper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

abstract class AbstractNode<N : Any, S : Any, E : Any, VM : AbstractViewModel<S, E>>(
    buildContext: BuildContext, protected val navigation: N
) : Node(buildContext) {

    protected lateinit var context: Context

    protected lateinit var viewModel: VM

    protected open fun mapEffect(effect: E) = Unit

    @Composable
    abstract fun obtainViewModel(): VM

    @Composable
    abstract fun Screen(
        modifier: Modifier, state: S
    )

    @CallSuper
    @Composable
    override fun View(modifier: Modifier) {
        viewModel = obtainViewModel()
        context = LocalContext.current
        viewModel.collectEffects { mapEffect(it) }
        val state = viewModel.state.collectAsState().value
        Screen(modifier = modifier, state = state)
    }
}

