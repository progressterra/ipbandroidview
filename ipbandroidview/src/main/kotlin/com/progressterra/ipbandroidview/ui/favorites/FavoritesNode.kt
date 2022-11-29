package com.progressterra.ipbandroidview.ui.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class FavoritesNode(
    buildContext: BuildContext,
    private val onGoodsDetails: (String) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: FavoritesViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is FavoritesEffect.GoodsDetails -> onGoodsDetails(it.goodsId)
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.collectAsState()
        FavoritesScreen(
            state = state::value,
            favoriteSpecific = viewModel::favoriteSpecific,
            refresh = viewModel::refresh,
            openDetails = viewModel::openDetails
        )
    }
}