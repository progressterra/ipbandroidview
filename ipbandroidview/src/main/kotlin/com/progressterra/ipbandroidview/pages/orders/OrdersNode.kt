package com.progressterra.ipbandroidview.pages.orders

import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsState
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class OrdersNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit,
    private val onGoodsDetails: (String) -> Unit,
    private val onTracking: () -> Unit,
    private val orderDetailsState: OrderDetailsState
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: OrdersViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is OrdersEvent.Back -> onBack()
                is OrdersEvent.GoodsDetails -> onGoodsDetails(it.goodsId)
                is OrdersEvent.Tracking -> onTracking()
            }
        }
        var alreadyLaunched by rememberSaveable(orderDetailsState) {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.refresh(orderDetailsState)
        }
        val state = viewModel.collectAsState()
        OrdersScreen(
            state = state.value,
            useComponent = viewModel
        )
    }
}