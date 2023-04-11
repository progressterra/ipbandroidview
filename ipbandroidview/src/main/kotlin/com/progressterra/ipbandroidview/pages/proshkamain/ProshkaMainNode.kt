package com.progressterra.ipbandroidview.pages.proshkamain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class ProshkaMainNode(
    buildContext: BuildContext,
    private val onBonuses: () -> Unit,
    private val onItem: () -> Unit,
    private val onOffer: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<ProshkaMainViewModel>()
        viewModel.collectSideEffect {
            when (it) {
                is ProshkaMainEvent.OnBonuses -> onBonuses()
                is ProshkaMainEvent.OnItem -> onItem()
                is ProshkaMainEvent.OnOffer -> onOffer()
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.collectAsState().value
        ProshkaMainScreen(
            state = state, useComponent = viewModel
        )
    }
}