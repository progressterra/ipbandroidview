package com.progressterra.ipbandroidview.signin

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class SignInNode(
    buildContext: BuildContext,
    private val onNext: () -> Unit,
    private val onSkip: () -> Unit,
    private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: SignInViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                SignInEffect.Back -> onBack()
                SignInEffect.Next -> onNext()
                SignInEffect.Skip -> onSkip()
            }
        }
        val state = viewModel.collectAsState().value
        SignInScreen(state = state, interactor = viewModel)
    }
}