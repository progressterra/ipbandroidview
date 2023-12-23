package com.progressterra.ipbandroidview.pages.occupacion

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class OccupationScreenNode(
    buildContext: BuildContext,
    private val navigation: OccupationScreenNavigation
) : Node(buildContext = buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<OccupationScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is OccupationScreenEffect.OnBack -> navigation.onBack()
                is OccupationScreenEffect.OnNext -> navigation.onNext()
                is OccupationScreenEffect.OnSkip -> navigation.onSkip()
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        OccupationScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}
