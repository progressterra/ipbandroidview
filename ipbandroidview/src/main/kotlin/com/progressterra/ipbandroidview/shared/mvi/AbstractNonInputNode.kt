package com.progressterra.ipbandroidview.shared.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext

abstract class AbstractNonInputNode<N : Any, S : Any, E : Any, VM : AbstractNonInputViewModel<S, E>>(
    buildContext: BuildContext, navigation: N
) : AbstractNode<N, S, E, VM>(buildContext, navigation) {

    @Composable
    override fun View(modifier: Modifier) {
        super.View(modifier)
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
    }
}