package com.progressterra.ipbandroidview.pages.readytomeet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class ReadyToMeetScreenNode(
    buildContext: BuildContext,
    private val navigation: ReadyToMeetNavigation
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<ReadyToMeetScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is ReadyToMeetScreenEffect.OnBack -> navigation.onBack()
                is ReadyToMeetScreenEffect.OnNext -> navigation.onNext()
            }
        }
        val state = viewModel.state.collectAsState().value
        ReadyToMeetScreen(
            modifier = modifier, state = state, useComponent = viewModel
        )
    }
}