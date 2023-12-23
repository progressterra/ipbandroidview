package com.progressterra.ipbandroidview.pages.avatarpicker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class AvatarPickerScreenNode(
    buildContext: BuildContext,
    private val navigation: AvatarPickerScreenNavigation
) : Node(buildContext = buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<AvatarPickerScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is AvatarPickerScreenEffect.OnBack -> navigation.onBack()
                is AvatarPickerScreenEffect.OnNext -> navigation.onNext()
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        AvatarPickerScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}