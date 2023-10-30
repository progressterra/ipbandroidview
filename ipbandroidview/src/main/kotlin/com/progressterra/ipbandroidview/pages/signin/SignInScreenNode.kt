package com.progressterra.ipbandroidview.pages.signin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class SignInScreenNode(
    buildContext: BuildContext,
    private val navigation: SignInScreenNavigation
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<SignInScreenViewModel>()
        viewModel.collectEffects {
            when (it) {
                is SignInScreenEffect.Next -> navigation.onCodeConfirmation(it.data)
                is SignInScreenEffect.Skip -> navigation.onMain()
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        SignInScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}