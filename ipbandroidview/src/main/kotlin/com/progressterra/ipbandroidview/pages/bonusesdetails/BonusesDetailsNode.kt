package com.progressterra.ipbandroidview.pages.bonusesdetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class BonusesDetailsNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: BonusesDetailsViewModel = getViewModel()
        viewModel.collectEffects {
            when (it) {
                is BonusesDetailsEvent.Back -> onBack()
            }
        }
        var alreadyLaunched by rememberSaveable {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.refresh()
        }
        val state = viewModel.state.value
        BonusesScreen(
            state = state,
            useComponent = viewModel
        )
    }
}