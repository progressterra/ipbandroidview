package com.progressterra.ipbandroidview.pages.orderlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class OrdersListNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit,
    private val onOrderDetails: (String) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: OrdersListViewModel = getViewModel()
        viewModel.collectEffects {
            when (it) {
                is OrdersListEvent.Back -> onBack()
                is OrdersListEvent.OpenDetails -> onOrderDetails(it.id)
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.state.collectAsState().value
        OrdersListScreen(
            state = state,
            useComponent = viewModel
        )
    }
}