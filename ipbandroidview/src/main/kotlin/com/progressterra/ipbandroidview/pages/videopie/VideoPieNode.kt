package com.progressterra.ipbandroidview.pages.videopie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class VideoPieNode(buildContext: BuildContext) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<VideoPieScreenViewModel>()
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) { viewModel.refresh() }
        VideoPieScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}