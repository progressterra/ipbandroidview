package com.progressterra.ipbandroidview.pages.payment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.pages.orderstatus.OrderStatusState
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class PaymentNode(
    buildContext: BuildContext,
    private val onNext: (OrderStatusState) -> Unit,
    private val onBack: () -> Unit
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<PaymentViewModel>()
        viewModel.collectEffects {
            when (it) {
                is PaymentEvent.Back -> onBack()
                is PaymentEvent.Next -> onNext(it.orderStatusState)
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.state.collectAsState().value
        PaymentScreen(
            state = state,
            useComponent = viewModel
        )
    }
}