package com.progressterra.ipbandroidview.pages.bonusesdetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class BonusesDetailsScreenNode(
    buildContext: BuildContext,
    private val navigation: BonusesDetailsScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<BonusesDetailsScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is BonusesDetailsScreenEffect.Back -> navigation.onBack()
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        BonusesScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}