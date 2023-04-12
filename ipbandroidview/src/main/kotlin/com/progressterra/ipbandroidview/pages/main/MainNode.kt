package com.progressterra.ipbandroidview.pages.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class MainNode(
    buildContext: BuildContext,
    private val onBonuses: () -> Unit,
    private val onItem: () -> Unit,
    private val onOffer: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<MainViewModel>()
        viewModel.collectSideEffect {
            when (it) {
                is MainEvent.OnBonuses -> onBonuses()
                is MainEvent.OnItem -> onItem()
                is MainEvent.OnOffer -> onOffer()
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.collectAsState().value
        MainScreen(
            state = state, useComponent = viewModel
        )
    }
}