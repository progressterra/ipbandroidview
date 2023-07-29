package com.progressterra.ipbandroidview.pages.support

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class SupportScreenNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<SupportScreenViewModel>()
        viewModel.collectEffects { onBack() }
        val state = viewModel.state.value
        SupportScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}