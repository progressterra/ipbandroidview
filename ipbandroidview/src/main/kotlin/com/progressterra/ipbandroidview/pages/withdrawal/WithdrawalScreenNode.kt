package com.progressterra.ipbandroidview.pages.withdrawal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.entities.Document
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class WithdrawalScreenNode(
    buildContext: BuildContext,
    private val navigation: WithdrawalScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<WithdrawalScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is WithdrawalScreenEffect.Back -> navigation.onBack()
                is WithdrawalScreenEffect.New -> navigation.onCreateWithdrawal()
                is WithdrawalScreenEffect.AddCard -> navigation.onBankCard(Document())
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        WithdrawalScreen(modifier = modifier, state = state, useComponent = viewModel)

    }
}