package com.progressterra.ipbandroidview.pages.bankcards

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class BankCardsScreenNode(
    buildContext: BuildContext,
    navigation: BankCardsScreenNavigation
) : AbstractNonInputNode<BankCardsScreenNavigation, BankCardsScreenState, BankCardsScreenEffect, BankCardsScreenViewModel>(
    buildContext,
    navigation
) {

    override fun mapEffect(effect: BankCardsScreenEffect) {
        when (effect) {
            is BankCardsScreenEffect.Back -> navigation.onBack()
            is BankCardsScreenEffect.OpenDetails -> navigation.onBankCard(effect.data)
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<BankCardsScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: BankCardsScreenState) {
        BankCardsScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}