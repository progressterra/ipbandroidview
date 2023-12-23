package com.progressterra.ipbandroidview.pages.orders

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class OrdersScreenNode(
    buildContext: BuildContext,
    private val navigation: OrdersScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<OrdersScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is OrdersScreenEffect.Back -> navigation.onBack()
                is OrdersScreenEffect.OpenDetails -> navigation.onOrderDetails(effect.id)
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        OrdersScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}