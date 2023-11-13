package com.progressterra.ipbandroidview.pages.workwatch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class WorkWatchScreenNode(
    buildContext: BuildContext
) : Node(buildContext = buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<WorkWatchScreenViewModel>()
        val state = viewModel.state.collectAsState().value
        WorkWatchScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}