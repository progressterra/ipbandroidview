package com.progressterra.ipbandroidview.pages.catalog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class CatalogScreenNode(
    buildContext: BuildContext, private val navigation: CatalogScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<CatalogScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is CatalogScreenEffect.OnItem -> navigation.onGoodsDetails(effect.data)
                is CatalogScreenEffect.OnAuth -> navigation.onAuth()
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        CatalogScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}