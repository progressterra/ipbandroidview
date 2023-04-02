package com.progressterra.ipbandroidview.pages.bonuses

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class BonusesNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit,
    private val onClarification: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: BonusesViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is BonusesEffect.Back -> onBack()
                is BonusesEffect.Clarification -> onClarification()
            }
        }
        val state = viewModel.collectAsState()
        BonusesScreen(
//            state = state.value,
//            interactor = viewModel
        )
    }


}