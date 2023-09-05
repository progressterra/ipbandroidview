package com.progressterra.ipbandroidview.pages.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class MainScreenNode(
    buildContext: BuildContext,
    private val navigation: MainScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<MainScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is MainScreenEffect.OnBonuses -> navigation.onBonuses()
                is MainScreenEffect.OnItem -> navigation.openGoodsDetails(effect.data)
                is MainScreenEffect.OnWithdrawal -> navigation.onWithdrawal()
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        MainScreen(
            modifier = modifier, state = state, useComponent = viewModel
        )
    }
}