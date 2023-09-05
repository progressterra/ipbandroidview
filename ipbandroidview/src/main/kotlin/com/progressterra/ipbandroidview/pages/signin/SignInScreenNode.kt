package com.progressterra.ipbandroidview.pages.signin

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputNode

@Suppress("unused")
class SignInScreenNode(
    buildContext: BuildContext,
    navigation: SignInScreenNavigation,
    private val provideViewModel: (Class<SignInScreenViewModel>) -> SignInScreenViewModel
) : AbstractNonInputNode<SignInScreenNavigation, SignInScreenState, SignInScreenEffect, SignInScreenViewModel>(
    buildContext,
    navigation
) {

    override fun mapEffect(effect: SignInScreenEffect) {
        when (effect) {
            is SignInScreenEffect.Next -> navigation.onCodeConfirmation(effect.data)
            is SignInScreenEffect.Skip -> navigation.onMain()
            is SignInScreenEffect.Toast -> Toast.makeText(context, effect.data, Toast.LENGTH_SHORT)
                .show()
        }
    }

    @Composable
    override fun obtainViewModel() = provideViewModel(SignInScreenViewModel::class.java)

    @Composable
    override fun Screen(modifier: Modifier, state: SignInScreenState) {
        SignInScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}