package com.progressterra.ipbandroidview.pages.bonusesdetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class BonusesDetailsNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: BonusesDetailsViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is BonusesDetailsEvent.Back -> onBack()
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.collectAsState()
        BonusesScreen(
            state = state.value,
            useComponent = viewModel
        )
    }


}