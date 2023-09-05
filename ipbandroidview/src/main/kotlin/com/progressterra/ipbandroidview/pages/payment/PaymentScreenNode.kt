package com.progressterra.ipbandroidview.pages.payment

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class PaymentScreenNode(
    buildContext: BuildContext,
    navigation: PaymentScreenNavigation
) : AbstractNonInputNode<PaymentScreenNavigation, PaymentScreenState, PaymentScreenEffect, PaymentScreenViewModel>(
    buildContext,
    navigation
) {

    @Composable
    override fun obtainViewModel() = getViewModel<PaymentScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: PaymentScreenState) {
        PaymentScreen(state = state, useComponent = viewModel)
    }

    override fun mapEffect(effect: PaymentScreenEffect) {
        when (effect) {
            is PaymentScreenEffect.Back -> navigation.onBack()
            is PaymentScreenEffect.Next -> navigation.onPaymentStatus(effect.data)
        }
    }
}