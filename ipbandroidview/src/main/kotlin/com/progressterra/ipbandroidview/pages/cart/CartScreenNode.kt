package com.progressterra.ipbandroidview.pages.cart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class CartScreenNode(
    buildContext: BuildContext,
    private val navigation: CartScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<CartScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is CartScreenEffect.OnItem -> navigation.onGoodsDetails(effect.data)
                is CartScreenEffect.Payment -> navigation.onDelivery()
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        CartScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}