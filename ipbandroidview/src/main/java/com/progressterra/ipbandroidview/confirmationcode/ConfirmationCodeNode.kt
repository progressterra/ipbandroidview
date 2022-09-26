package com.progressterra.ipbandroidview.confirmationcode

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class ConfirmationCodeNode(
    buildContext: BuildContext,
    private val onNext: () -> Unit,
    private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: ConfirmationCodeViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                ConfirmationEffect.Back -> onBack()
                ConfirmationEffect.Next -> onNext()
            }
        }
        val state = viewModel.collectAsState().value
        ConfirmationCodeScreen(state = state, interactor = viewModel)
    }
}