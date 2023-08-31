package com.progressterra.ipbandroidview.pages.orderdetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class OrderDetailsScreenNode(
    buildContext: BuildContext,
    navigation: OrderDetailsScreenNavigation,
    input: String
) : AbstractInputNode<String, OrderDetailsScreenNavigation, OrderDetailsScreenState, OrderDetailsScreenEffect, OrderDetailsScreenViewModel>(
    buildContext,
    navigation,
    input
) {

    override fun mapEffect(effect: OrderDetailsScreenEffect) {
        when (effect) {
            is OrderDetailsScreenEffect.Back -> navigation.onBack()
            is OrderDetailsScreenEffect.OpenGoods -> navigation.openGoodsDetails(effect.data)
            is OrderDetailsScreenEffect.Tracking -> navigation.onTracking(effect.data)
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<OrderDetailsScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: OrderDetailsScreenState) {
        OrderDetailsScreen(
            modifier = modifier, state = state, useComponent = viewModel
        )
    }
}