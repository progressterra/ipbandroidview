package com.progressterra.ipbandroidview.pages.overview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class OverviewNode(
    buildContext: BuildContext,
    private val navigation: OverviewScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<OverviewScreenViewModel>()
        viewModel.collectEffects {
            when (it) {
                is OverviewEffect.OnChecklist -> navigation.onChecklist(it.data)
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        OverviewScreen(
            state = state, useComponent = viewModel
        )
    }
}