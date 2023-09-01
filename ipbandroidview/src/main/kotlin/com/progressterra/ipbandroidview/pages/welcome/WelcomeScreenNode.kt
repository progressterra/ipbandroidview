package com.progressterra.ipbandroidview.pages.welcome

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class WelcomeScreenNode(
    buildContext: BuildContext,
    navigation: WelcomeScreenNavigation
) : AbstractNonInputNode<WelcomeScreenNavigation, WelcomeScreenState, WelcomeScreenEffect, WelcomeScreenViewModel>(
    buildContext,
    navigation
) {

    override fun mapEffect(effect: WelcomeScreenEffect) {
        when (effect) {
            is WelcomeScreenEffect.OnAuth -> navigation.onAuth()
            is WelcomeScreenEffect.OnSkip -> navigation.onSkip()
            is WelcomeScreenEffect.OnAlreadyAuth -> navigation.onAlreadyAuth()
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<WelcomeScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: WelcomeScreenState) {
        WelcomeScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}