package com.progressterra.ipbandroidview.pages.overview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class OverviewNode(
    buildContext: BuildContext,
    private val navigation: OverviewScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: OverviewScreenViewModel = getViewModel()
        viewModel.collectEffects {
            when (it) {
                is OverviewEffect.Archive -> navigation.onArchive(it.title, it.archived)
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