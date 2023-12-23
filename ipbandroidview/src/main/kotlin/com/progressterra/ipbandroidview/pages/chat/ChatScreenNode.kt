package com.progressterra.ipbandroidview.pages.chat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class ChatScreenNode(
    buildContext: BuildContext,
    private val navigation: ChatScreenNavigation,
    private val input: String
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<ChatScreenViewModel>()
        viewModel.collectEffects {
            navigation.onBack()
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(input) {
            viewModel.setup(input)
        }
        ChatScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}