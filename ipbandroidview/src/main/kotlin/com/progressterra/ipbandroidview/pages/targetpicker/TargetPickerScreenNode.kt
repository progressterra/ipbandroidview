package com.progressterra.ipbandroidview.pages.targetpicker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class TargetPickerScreenNode(
    buildContext: BuildContext,
    private val navigation: TargetPickerNavigation
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<TargetPickerScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is TargetPickerScreenEffect.OnBack -> navigation.onBack()
                is TargetPickerScreenEffect.OnNext -> navigation.onReadyToMeet(effect.target)
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        TargetPickerScreen(
            modifier = modifier, state = state, useComponent = viewModel
        )
    }
}