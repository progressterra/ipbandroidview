package com.progressterra.ipbandroidview.pages.orderdetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.features.ordertracking.OrderTrackingState
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class OrderDetailsScreenNode(
    buildContext: BuildContext,
    private val orderId: String,
    private val onBack: () -> Unit,
    private val onGoods: (String) -> Unit,
    private val onTracking: (OrderTrackingState) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<OrderDetailsScreenViewModel>()
        viewModel.collectEffects {
            when (it) {
                is OrderDetailsScreenEvent.Back -> onBack()
                is OrderDetailsScreenEvent.OpenGoods -> onGoods(it.goodsId)
                is OrderDetailsScreenEvent.Tracking -> onTracking(it.tracking)
            }
        }
        LaunchedEffect(orderId) {
            viewModel.setupId(orderId)
            viewModel.refresh()
        }
        val state = viewModel.state.value
        OrderDetailsScreen(
            modifier = modifier, state = state, useComponent = viewModel
        )
    }
}