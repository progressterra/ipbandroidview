package com.progressterra.ipbandroidview.pages.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class SignUpScreenNode(
    buildContext: BuildContext,
    private val navigation: SignUpScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<SignUpScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is SignUpScreenEffect.OnBack -> navigation.onBack()
                is SignUpScreenEffect.OnNext -> navigation.onMain()
                is SignUpScreenEffect.OnSkip -> navigation.onMain()
                is SignUpScreenEffect.OpenPhoto -> navigation.openPhoto(effect.data)
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        SignUpScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}