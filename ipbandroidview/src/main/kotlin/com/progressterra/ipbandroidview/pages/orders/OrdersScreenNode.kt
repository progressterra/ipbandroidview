package com.progressterra.ipbandroidview.pages.orders

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class OrdersScreenNode(
    buildContext: BuildContext,
    navigation: OrdersScreenNavigation
) : AbstractNonInputNode<OrdersScreenNavigation, OrdersScreenState, OrdersScreenEffect, OrdersScreenViewModel>(
    buildContext,
    navigation
) {

    override fun mapEffect(effect: OrdersScreenEffect) {
        when (effect) {
            is OrdersScreenEffect.Back -> navigation.onBack()
            is OrdersScreenEffect.OpenDetails -> navigation.onOrder(effect.id)
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<OrdersScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: OrdersScreenState) {
        OrdersScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}