package com.progressterra.ipbandroidview.pages.newwithdrawal

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class NewWithdrawalScreenNode(
    buildContext: BuildContext,
    navigation: NewWithdrawalScreenNavigation
) : AbstractNonInputNode<NewWithdrawalScreenNavigation, NewWithdrawalScreenState, NewWithdrawalScreenEffect, NewWithdrawalScreenViewModel>(
    buildContext,
    navigation
) {

    override fun mapEffect(effect: NewWithdrawalScreenEffect) {
        when (effect) {
            is NewWithdrawalScreenEffect.Back -> navigation.onBack()
            is NewWithdrawalScreenEffect.Toast -> Toast.makeText(
                context,
                effect.data,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<NewWithdrawalScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: NewWithdrawalScreenState) {
        NewWithdrawalScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}