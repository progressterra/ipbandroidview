package com.progressterra.ipbandroidview.pages.orderlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsState
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class OrdersListNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit,
    private val onOrderDetails: (OrderDetailsState) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: OrdersListViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is OrdersListEvent.Back -> onBack()
                is OrdersListEvent.OpenDetails -> onOrderDetails(it.state)
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.collectAsState()
        OrdersListScreen(
            state = state.value,
            useComponent = viewModel
        )
    }
}