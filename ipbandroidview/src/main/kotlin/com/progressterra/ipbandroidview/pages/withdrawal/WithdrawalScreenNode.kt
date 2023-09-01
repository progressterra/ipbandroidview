package com.progressterra.ipbandroidview.pages.withdrawal

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class WithdrawalScreenNode(
    buildContext: BuildContext,
    navigation: WithdrawalScreenNavigation
) : AbstractNonInputNode<WithdrawalScreenNavigation, WithdrawalScreenState, WithdrawalScreenEffect, WithdrawalScreenViewModel>(
    buildContext,
    navigation
) {

    override fun mapEffect(effect: WithdrawalScreenEffect) {
        when (effect) {
            is WithdrawalScreenEffect.Back -> navigation.onBack()
            is WithdrawalScreenEffect.New -> navigation.onCreate()
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<WithdrawalScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: WithdrawalScreenState) {
        WithdrawalScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}