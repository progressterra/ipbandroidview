package com.progressterra.ipbandroidview.pages.interests

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class InterestsNode(
    buildContext: BuildContext,
    private val navigation: InterestsScreenNavigation
) : Node(buildContext = buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<InterestsScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is InterestsScreenEffect.OnBack -> navigation.onBack()
                is InterestsScreenEffect.OnNext -> navigation.onNext()
                is InterestsScreenEffect.OnSkip -> navigation.onSkip()
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        InterestsScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}
