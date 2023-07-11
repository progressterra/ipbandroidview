package com.progressterra.ipbandroidview.pages.ordertracking

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.features.ordertracking.OrderTrackingState
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class OrderTrackingScreenNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit,
    private val tracking: OrderTrackingState
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<OrderTrackingScreenViewModel>()
        viewModel.collectSideEffect { onBack() }
        var alreadyLaunched by rememberSaveable(tracking) {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.refresh(tracking)
        }
        val state = viewModel.collectAsState().value
        OrderTrackingScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}