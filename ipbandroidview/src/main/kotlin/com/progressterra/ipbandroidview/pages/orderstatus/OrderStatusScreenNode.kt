package com.progressterra.ipbandroidview.pages.orderstatus

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class OrderStatusScreenNode(
    buildContext: BuildContext,
    private val navigation: OrderStatusScreenNavigation,
    private val input: OrderStatusScreenState
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<OrderStatusScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is OrderStatusScreenEffect.OnBack -> navigation.onBack()
                is OrderStatusScreenEffect.OnMain -> navigation.onMain()
                is OrderStatusScreenEffect.OnOrder -> navigation.onOrderDetails(effect.data)
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(input) {
            viewModel.setup(input)
        }
        OrderStatusScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}