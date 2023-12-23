package com.progressterra.ipbandroidview.pages.payment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class PaymentScreenNode(
    buildContext: BuildContext,
    private val navigation: PaymentScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<PaymentScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is PaymentScreenEffect.Back -> navigation.onBack()
                is PaymentScreenEffect.Next -> navigation.onPaymentStatus(effect.data)
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        PaymentScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}