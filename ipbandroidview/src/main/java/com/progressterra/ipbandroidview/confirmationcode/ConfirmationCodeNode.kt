package com.progressterra.ipbandroidview.confirmationcode

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class ConfirmationCodeNode(
    buildContext: BuildContext,
    private val onNext: () -> Unit,
    private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: ConfirmationCodeViewModel =
            getViewModel()
        val context = LocalContext.current
        viewModel.collectSideEffect {
            when (it) {
                is ConfirmationEffect.Back -> onBack()
                is ConfirmationEffect.Next -> onNext()
                is ConfirmationEffect.Toast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        val state = viewModel.collectAsState().value
        ConfirmationCodeScreen(state = state, interactor = viewModel)
    }
}