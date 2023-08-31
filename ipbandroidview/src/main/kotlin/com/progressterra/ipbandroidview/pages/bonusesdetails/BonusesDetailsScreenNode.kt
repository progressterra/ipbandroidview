package com.progressterra.ipbandroidview.pages.bonusesdetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class BonusesDetailsScreenNode(
    buildContext: BuildContext,
    navigation: BonusesDetailsScreenNavigation
) : AbstractNonInputNode<BonusesDetailsScreenNavigation, BonusesDetailsScreenState, BonusesDetailsScreenEffect, BonusesDetailsScreenViewModel>(
    buildContext,
    navigation
) {

    override fun mapEffect(effect: BonusesDetailsScreenEffect) {
        when (effect) {
            is BonusesDetailsScreenEffect.Back -> navigation.onBack()
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<BonusesDetailsScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: BonusesDetailsScreenState) {
        BonusesScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}