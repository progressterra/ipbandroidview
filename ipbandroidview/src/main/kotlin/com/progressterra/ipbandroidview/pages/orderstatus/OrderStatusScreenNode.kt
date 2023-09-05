package com.progressterra.ipbandroidview.pages.orderstatus

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class OrderStatusScreenNode(
    buildContext: BuildContext,
    navigation: OrderStatusScreenNavigation,
    input: OrderStatusScreenState
) : AbstractInputNode<OrderStatusScreenState, OrderStatusScreenNavigation, OrderStatusScreenState, OrderStatusScreenEffect, OrderStatusScreenViewModel>(
    buildContext,
    navigation,
    input
) {

    override fun mapEffect(effect: OrderStatusScreenEffect) {
        when (effect) {
            is OrderStatusScreenEffect.OnBack -> navigation.onBack()
            is OrderStatusScreenEffect.OnMain -> navigation.onMain()
            is OrderStatusScreenEffect.OnOrder -> navigation.onOrderDetails(effect.data)
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<OrderStatusScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: OrderStatusScreenState) {
        OrderStatusScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}