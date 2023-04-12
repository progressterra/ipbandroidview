package com.progressterra.ipbandroidview.pages.welcome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class WelcomeNode(
    buildContext: BuildContext,
    private val onAuth: () -> Unit,
    private val onSkip: (() -> Unit)? = null,
    private val onAlreadyAuth: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<WelcomeViewModel>()
        viewModel.collectSideEffect {
            when (it) {
                is WelcomeEvent.OnAuth -> onAuth()
                is WelcomeEvent.OnSkip -> onSkip?.invoke()
                is WelcomeEvent.OnAlreadyAuth -> onAlreadyAuth()
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.collectAsState().value
        WelcomeScreen(
            state = state,
            useComponent = viewModel
        )
    }
}