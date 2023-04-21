package com.progressterra.ipbandroidview.pages.orderstatus

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class OrderStatusNode(
    buildContext: BuildContext,
    private val orderStatusState: OrderStatusState,
    private val onMain: () -> Unit,
    private val onBack: () -> Unit,
    private val onRecipe: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<OrderStatusViewModel>()
        viewModel.collectSideEffect {
            when (it) {
                is OrderStatusEvent.OnBack -> onBack()
                is OrderStatusEvent.OnMain -> onMain()
                is OrderStatusEvent.OnRecipe -> onRecipe()
            }
        }
        val state = viewModel.collectAsState().value
        OrderStatusScreen(
            state = state,
            useComponent = viewModel
        )
    }
}