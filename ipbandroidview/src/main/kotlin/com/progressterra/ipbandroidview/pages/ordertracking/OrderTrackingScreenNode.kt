package com.progressterra.ipbandroidview.pages.ordertracking

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.features.ordertracking.OrderTrackingState
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class OrderTrackingScreenNode(
    buildContext: BuildContext,
    navigation: OrderTrackingScreenNavigation,
    input: OrderTrackingState
) : AbstractInputNode<OrderTrackingState, OrderTrackingScreenNavigation, OrderTrackingScreenState, OrderTrackingScreenEffect, OrderTrackingScreenViewModel>(
    buildContext,
    navigation,
    input
) {

    override fun mapEffect(effect: OrderTrackingScreenEffect) {
        navigation.onBack()
    }

    @Composable
    override fun obtainViewModel() = getViewModel<OrderTrackingScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: OrderTrackingScreenState) {
        OrderTrackingScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}