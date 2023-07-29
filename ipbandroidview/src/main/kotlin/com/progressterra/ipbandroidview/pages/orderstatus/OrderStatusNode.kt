package com.progressterra.ipbandroidview.pages.orderstatus

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class OrderStatusNode(
    buildContext: BuildContext,
    private val orderStatusState: OrderStatusState,
    private val onMain: () -> Unit,
    private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<OrderStatusViewModel>()
        viewModel.collectEffects {
            when (it) {
                is OrderStatusEvent.OnBack -> onBack()
                is OrderStatusEvent.OnMain -> onMain()
            }
        }
        var alreadyLaunched by rememberSaveable(orderStatusState) {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.setup(orderStatusState)
        }
        val state = viewModel.state.collectAsState().value
        OrderStatusScreen(
            state = state,
            useComponent = viewModel
        )
    }
}