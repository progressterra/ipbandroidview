package com.progressterra.ipbandroidview.pages.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class MainScreenNode(
    buildContext: BuildContext,
    navigation: MainScreenNavigation
) : AbstractNonInputNode<MainScreenNavigation, MainScreenState, MainScreenEffect, MainScreenViewModel>(
    buildContext,
    navigation
) {

    override fun mapEffect(effect: MainScreenEffect) {
        when (effect) {
            is MainScreenEffect.OnBonuses -> navigation.onBonuses()
            is MainScreenEffect.OnItem -> navigation.openGoodsDetails(effect.data)
            is MainScreenEffect.OnWithdrawal -> navigation.onWithdrawal()
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<MainScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: MainScreenState) {
        MainScreen(
            modifier = modifier, state = state, useComponent = viewModel
        )
    }
}