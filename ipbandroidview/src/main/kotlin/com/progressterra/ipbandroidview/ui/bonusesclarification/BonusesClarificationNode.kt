package com.progressterra.ipbandroidview.ui.bonusesclarification

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class BonusesClarificationNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: BonusesClarificationViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is BonusesClarificationEffect.Back -> onBack()
            }
        }
        val state = viewModel.collectAsState()
        BonusesClarificationScreen(
            state = state::value,
            back = viewModel::back,
            expandHowToObtain = viewModel::expandHowToObtain,
            expandHowToSpend = viewModel::expandHowToSpend,
            expandRatio = viewModel::expandRatio
        )
    }
}