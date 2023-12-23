package com.progressterra.ipbandroidview.pages.orderdetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class OrderDetailsScreenNode(
    buildContext: BuildContext,
    private val navigation: OrderDetailsScreenNavigation,
    private val input: String
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<OrderDetailsScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is OrderDetailsScreenEffect.Back -> navigation.onBack()
                is OrderDetailsScreenEffect.OpenGoods -> navigation.onGoodsDetails(effect.data)
                is OrderDetailsScreenEffect.Tracking -> navigation.onTracking(effect.data)
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(input) {
            viewModel.setup(input)
        }
        OrderDetailsScreen(
            modifier = modifier, state = state, useComponent = viewModel
        )
    }
}