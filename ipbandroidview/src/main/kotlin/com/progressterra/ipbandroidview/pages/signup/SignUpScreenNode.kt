package com.progressterra.ipbandroidview.pages.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class SignUpScreenNode(
    buildContext: BuildContext,
    private val navigation: SignUpScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<SignUpScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is SignUpScreenEffect.OnNext -> navigation.onNext()
                is SignUpScreenEffect.OnSkip -> navigation.onSkip()
            }
        }
        val state = viewModel.state.collectAsState().value
        val focusManager = LocalFocusManager.current
        LaunchedEffect(Unit) {
            focusManager.clearFocus()
            viewModel.refresh()
        }
        SignUpScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}