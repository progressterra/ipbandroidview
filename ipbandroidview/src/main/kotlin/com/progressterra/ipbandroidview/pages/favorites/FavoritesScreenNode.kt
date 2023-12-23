package com.progressterra.ipbandroidview.pages.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class FavoritesScreenNode(
    buildContext: BuildContext,
    private val navigation: FavoritesScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<FavoritesScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is FavoritesScreenEffect.GoodsDetails -> navigation.onGoodsDetails(effect.data)
                is FavoritesScreenEffect.Back -> navigation.onBack()
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        FavoritesScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}