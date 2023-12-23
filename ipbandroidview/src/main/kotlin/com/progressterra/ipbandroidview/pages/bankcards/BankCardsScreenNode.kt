package com.progressterra.ipbandroidview.pages.bankcards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class BankCardsScreenNode(
    buildContext: BuildContext,
    private val navigation: BankCardsScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<BankCardsScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is BankCardsScreenEffect.Back -> navigation.onBack()
                is BankCardsScreenEffect.OpenDetails -> navigation.onBankCard(effect.data)
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        BankCardsScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}