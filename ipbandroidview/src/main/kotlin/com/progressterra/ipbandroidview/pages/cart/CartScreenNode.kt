package com.progressterra.ipbandroidview.pages.cart

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class CartScreenNode(
    buildContext: BuildContext,
    navigation: CartScreenNavigation
) : AbstractNonInputNode<CartScreenNavigation, CartScreenState, CartScreenEffect, CartScreenViewModel>(
    buildContext,
    navigation
) {

    override fun mapEffect(effect: CartScreenEffect) {
        when (effect) {
            is CartScreenEffect.OnItem -> navigation.openGoodsDetails(effect.data)
            is CartScreenEffect.Payment -> navigation.onNext()
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<CartScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: CartScreenState) {
        CartScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}