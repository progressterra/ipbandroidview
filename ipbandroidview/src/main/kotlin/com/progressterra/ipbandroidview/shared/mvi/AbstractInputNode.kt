package com.progressterra.ipbandroidview.shared.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext

abstract class AbstractInputNode<I : Any, N : Any, S : Any, E : Any, VM : AbstractInputViewModel<I, S, E>>(
    buildContext: BuildContext, navigation: N, private val input: I
) : AbstractNode<N, S, E, VM>(buildContext, navigation) {

    @Composable
    override fun View(modifier: Modifier) {
        super.View(modifier)
        LaunchedEffect(input) {
            viewModel.setup(input)
        }
    }
}