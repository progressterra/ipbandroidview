package com.progressterra.ipbandroidview.pages.readytomeet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.entities.DatingTarget
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class ReadyToMeetScreenNode(
    buildContext: BuildContext,
    private val input: DatingTarget,
    private val navigation: ReadyToMeetNavigation
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<ReadyToMeetScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is ReadyToMeetScreenEffect.OnBack -> navigation.onBack()
                is ReadyToMeetScreenEffect.OnNext -> navigation.onNext()
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(input) {
            viewModel.setup(input)
        }
        ReadyToMeetScreen(
            modifier = modifier, state = state, useComponent = viewModel
        )
    }
}