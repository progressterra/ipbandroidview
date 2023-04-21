package com.progressterra.ipbandroidview.pages.goodsdetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class GoodsDetailsNode(
    buildContext: BuildContext,
    private val goodsId: String,
    private val onBack: () -> Unit,
    private val openPhoto: (String) -> Unit
) : Node(
    buildContext = buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<GoodsDetailsViewModel>()
        viewModel.collectSideEffect {
            when (it) {
                is GoodsDetailsEvent.Back -> onBack()
                is GoodsDetailsEvent.OpenImage -> openPhoto(it.image)
            }
        }
        LaunchedEffect(goodsId) {
            viewModel.refresh(goodsId)
        }
        val state = viewModel.collectAsState().value
        GoodsDetailsScreen(state = state, useComponent = viewModel)
    }
}