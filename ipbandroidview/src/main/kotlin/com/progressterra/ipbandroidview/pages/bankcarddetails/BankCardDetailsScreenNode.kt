package com.progressterra.ipbandroidview.pages.bankcarddetails

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class BankCardDetailsScreenNode(
    buildContext: BuildContext,
    input: Document,
    navigation: BankCardDetailsScreenNavigation
) : AbstractInputNode<Document, BankCardDetailsScreenNavigation, BankCardDetailsScreenState, BankCardDetailsScreenEffect, BankCardDetailsScreenViewModel>(
    buildContext,
    navigation,
    input
) {

    override fun mapEffect(effect: BankCardDetailsScreenEffect) {
        when (effect) {
            is BankCardDetailsScreenEffect.Back -> navigation.onBack()
            is BankCardDetailsScreenEffect.OpenPhoto -> navigation.openPhoto(effect.data)
            is BankCardDetailsScreenEffect.Toast -> Toast.makeText(
                context,
                effect.data,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<BankCardDetailsScreenViewModel>()

    @Composable
    override fun Screen(
        modifier: Modifier,
        state: BankCardDetailsScreenState
    ) {
        BankCardDetailsScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}