package com.progressterra.ipbandroidview.pages.catalog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class CatalogNode(
    buildContext: BuildContext,
    private val onItem: (String) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<CatalogViewModel>()
        viewModel.collectSideEffect {
            when (it) {
                is CatalogEvent.OnItem -> onItem(it.id)
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.collectAsState().value
        CatalogScreen(
            state = state,
            useComponent = viewModel
        )
    }
}