package com.progressterra.ipbandroidview.pages.payment

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class PaymentNode(
    buildContext: BuildContext,
    private val onNext: () -> Unit,
    private val onBack: () -> Unit
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<PaymentViewModel>()
        viewModel.collectSideEffect {
            when (it) {
                is PaymentEvent.Back -> onBack()
                is PaymentEvent.Next -> onNext()
            }
        }
        val state = viewModel.collectAsState().value
        PaymentScreen(
            state = state,
            useComponent = viewModel
        )
    }
}