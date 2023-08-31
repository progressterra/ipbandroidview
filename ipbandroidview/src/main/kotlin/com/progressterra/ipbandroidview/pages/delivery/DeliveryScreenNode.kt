package com.progressterra.ipbandroidview.pages.delivery

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class DeliveryScreenNode(
    buildContext: BuildContext,
    navigation: DeliveryScreenNavigation
) : AbstractNonInputNode<DeliveryScreenNavigation, DeliveryScreenState, DeliveryScreenEffect, DeliveryViewModel>(
    buildContext,
    navigation
) {

    override fun mapEffect(effect: DeliveryScreenEffect) {
        when (effect) {
            is DeliveryScreenEffect.Back -> navigation.onBack()
            is DeliveryScreenEffect.Next -> navigation.onNext()
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<DeliveryViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: DeliveryScreenState) {
        DeliveryScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}