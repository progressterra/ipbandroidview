package com.progressterra.ipbandroidview.pages.welcome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class WelcomeNode(
    buildContext: BuildContext,
    private val onAuth: () -> Unit,
    private val onSkip: (() -> Unit)? = null,
    private val onAlreadyAuth: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<WelcomeViewModel>()
        viewModel.collectEffects {
            when (it) {
                is WelcomeEvent.OnAuth -> onAuth()
                is WelcomeEvent.OnSkip -> onSkip?.invoke()
                is WelcomeEvent.OnAlreadyAuth -> onAlreadyAuth()
            }
        }
        var alreadyLaunched by rememberSaveable {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.refresh()
        }
        val state = viewModel.state.collectAsState().value
        WelcomeScreen(
            state = state, useComponent = viewModel
        )
    }
}