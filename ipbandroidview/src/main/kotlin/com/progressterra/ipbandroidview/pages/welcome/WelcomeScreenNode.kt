package com.progressterra.ipbandroidview.pages.welcome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class WelcomeScreenNode(
    buildContext: BuildContext,
    private val navigation: WelcomeScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel =  getViewModel<WelcomeScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is WelcomeScreenEffect.OnAuth -> navigation.onAuth()
                is WelcomeScreenEffect.OnSkip -> navigation.onMain()
                is WelcomeScreenEffect.OnAlreadyAuth -> navigation.onMain()
            }        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        WelcomeScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}