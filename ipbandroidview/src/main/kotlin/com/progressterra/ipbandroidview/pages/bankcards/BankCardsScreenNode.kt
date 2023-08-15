package com.progressterra.ipbandroidview.pages.bankcards

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.pages.bankcarddetails.BankCardDetailsScreenState
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class BankCardsScreenNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit,
    private val onDetails: (BankCardDetailsScreenState) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<BankCardsScreenViewModel>()
        viewModel.collectEffects {
            when (it) {
                is BankCardsScreenEvent.Back -> onBack()
                is BankCardsScreenEvent.OpenDetails -> onDetails(it.state)
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.state.collectAsState().value
        BankCardsScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}