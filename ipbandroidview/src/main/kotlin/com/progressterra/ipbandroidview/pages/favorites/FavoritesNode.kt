package com.progressterra.ipbandroidview.pages.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class FavoritesNode(
    buildContext: BuildContext,
    private val onGoodsDetails: (String) -> Unit,
    private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: FavoritesViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is FavoritesEvent.GoodsDetails -> onGoodsDetails(it.goodsId)
                is FavoritesEvent.Back -> onBack()
            }
        }
        var alreadyLaunched by rememberSaveable {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.refresh()
        }
        val state = viewModel.collectAsState()
        FavoritesScreen(
            state = state.value,
            useComponent = viewModel
        )
    }
}