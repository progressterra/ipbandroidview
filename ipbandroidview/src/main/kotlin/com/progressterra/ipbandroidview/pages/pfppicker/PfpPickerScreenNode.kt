package com.progressterra.ipbandroidview.pages.pfppicker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class PfpPickerScreenNode(
    buildContext: BuildContext,
    private val navigation: PfpPickerScreenNavigation
) : Node(
    buildContext = buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<PfpPickerScreenViewModel>()
        val context = LocalContext.current
        viewModel.collectEffects { effect ->
            when (effect) {
                is PfpPickerScreenEffect.Back -> navigation.onBack()
                is PfpPickerScreenEffect.Next -> navigation.onNext()
                is PfpPickerScreenEffect.Skip -> navigation.onSkip()
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        PfpPickerScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}