package com.progressterra.ipbandroidview.pages.delivery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class DeliveryScreenNode(
    buildContext: BuildContext,
    private val navigation: DeliveryScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<DeliveryScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is DeliveryScreenEffect.Back -> navigation.onBack()
                is DeliveryScreenEffect.Next -> navigation.onPayment()
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        DeliveryScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}