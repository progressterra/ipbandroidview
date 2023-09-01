package com.progressterra.ipbandroidview.pages.signin

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class SignInScreenNode(
    buildContext: BuildContext,
    navigation: SignInScreenNavigation
) : AbstractNonInputNode<SignInScreenNavigation, SignInScreenState, SignInScreenEffect, SignInScreenViewModel>(
    buildContext,
    navigation
) {

    override fun mapEffect(effect: SignInScreenEffect) {
        when (effect) {
            is SignInScreenEffect.Next -> navigation.onNext(effect.data)
            is SignInScreenEffect.Skip -> navigation.onSkip()
            is SignInScreenEffect.Toast -> Toast.makeText(context, effect.data, Toast.LENGTH_SHORT)
                .show()
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<SignInScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: SignInScreenState) {
        SignInScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}