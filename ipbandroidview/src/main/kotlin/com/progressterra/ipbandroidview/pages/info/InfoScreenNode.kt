package com.progressterra.ipbandroidview.pages.info

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class InfoScreenNode(
    buildContext: BuildContext,
    private val navigation: InfoScreenNavigation
) : Node(buildContext = buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<InfoScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is InfoScreenEffect.OnBack -> navigation.onBack()
                is InfoScreenEffect.OnNext -> navigation.onNext()
                is InfoScreenEffect.OnSkip -> navigation.onSkip()
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        InfoScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}