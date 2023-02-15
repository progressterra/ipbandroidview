package com.progressterra.ipbandroidview.ui.orderprocessing

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.model.OrderResult
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class OrderProcessingNode(
    buildContext: BuildContext,
    private val orderResult: OrderResult,
    private val onBack: () -> Unit,
    private val onNext: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: OrderProcessingViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is OrderProcessingEffect.Back -> onBack()
                is OrderProcessingEffect.Next -> onNext()
            }
        }
        viewModel.setOrderResult(orderResult)
        val state = viewModel.collectAsState()
        OrderProcessingScreen(
            state = state.value,
            interactor = viewModel
        )
    }
}