package com.progressterra.ipbandroidview.pages.confirmationcode

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class ConfirmationCodeScreenNode(
    buildContext: BuildContext,
    input: String,
    navigation: ConfirmationCodeScreenNavigation
) : AbstractInputNode<String, ConfirmationCodeScreenNavigation, ConfirmationCodeScreenState, ConfirmationCodeScreenEffect, ConfirmationCodeScreenViewModel>(
    buildContext,
    navigation,
    input
) {

    override fun mapEffect(effect: ConfirmationCodeScreenEffect) {
        when (effect) {
            is ConfirmationCodeScreenEffect.Toast -> {
                Toast.makeText(context, effect.data, Toast.LENGTH_SHORT).show()
            }

            is ConfirmationCodeScreenEffect.Back -> navigation.onBack()
            is ConfirmationCodeScreenEffect.Next -> navigation.onNext()
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<ConfirmationCodeScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: ConfirmationCodeScreenState) {
        ConfirmationCodeScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}