package com.progressterra.ipbandroidview.pages.ordertracking

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.features.ordertracking.OrderTrackingState
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class OrderTrackingScreenNode(
    buildContext: BuildContext,
    private val navigation: OrderTrackingScreenNavigation,
    private val input: OrderTrackingState
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<OrderTrackingScreenViewModel>()
        viewModel.collectEffects {
            navigation.onBack()
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(input) {
            viewModel.setup(input)
        }
        OrderTrackingScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}