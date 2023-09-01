package com.progressterra.ipbandroidview.pages.signup

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class SignUpScreenNode(
    buildContext: BuildContext,
    navigation: SignUpScreenNavigation
) : AbstractNonInputNode<SignUpScreenNavigation, SignUpScreenState, SignUpScreenEffect, SignUpScreenViewModel>(
    buildContext,
    navigation
) {

    override fun mapEffect(effect: SignUpScreenEffect) {
        when (effect) {
            is SignUpScreenEffect.OnBack -> navigation.onBack()
            is SignUpScreenEffect.OnNext -> navigation.onNext()
            is SignUpScreenEffect.OnSkip -> navigation.onSkip()
            is SignUpScreenEffect.OpenPhoto -> navigation.openPhoto(effect.data)
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<SignUpScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: SignUpScreenState) {
        SignUpScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}