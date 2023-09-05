package com.progressterra.ipbandroidview.pages.photo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class PhotoScreenNode(
    buildContext: BuildContext,
    private val navigation: PhotoScreenNavigation,
    private val input: String
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<PhotoScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is PhotoScreenEffect.Back -> navigation.onBack()
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(input) {
            viewModel.setup(input)
        }
        PhotoScreen(
            modifier = modifier, state = state, useComponent = viewModel
        )
    }
}