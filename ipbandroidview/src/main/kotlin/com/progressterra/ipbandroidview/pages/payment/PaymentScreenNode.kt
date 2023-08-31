package com.progressterra.ipbandroidview.pages.payment

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class PaymentScreenNode(
    buildContext: BuildContext,
    navigation: PaymentScreenNavigation
) : AbstractNode<Unit, PaymentScreenNavigation, PaymentScreenState, PaymentScreenEffect, PaymentScreenViewModel>(
    buildContext,
    Unit,
    navigation
) {

    @Composable
    override fun obtainViewModel() = getViewModel<PaymentScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: PaymentScreenState, viewModel: PaymentScreenViewModel) {
        PaymentScreen(state = state, useComponent = viewModel)
    }

    override fun mapEffect(effect: PaymentScreenEffect) {
        when (effect) {
            is PaymentScreenEffect.Back -> navigation.onBack()
            is PaymentScreenEffect.Next -> navigation.onNext(effect.data)
        }
    }
}