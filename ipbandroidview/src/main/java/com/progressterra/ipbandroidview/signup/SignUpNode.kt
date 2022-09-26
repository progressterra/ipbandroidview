package com.progressterra.ipbandroidview.signup

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class SignUpNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit,
    private val onNext: () -> Unit,
    private val onSkip: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: SignUpViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                SignUpEffect.Back -> onBack()
                SignUpEffect.Next -> onNext()
                SignUpEffect.Skip -> onSkip()
            }
        }
        val state = viewModel.collectAsState().value
        SignUpScreen(state = state, interactor = viewModel)
    }
}