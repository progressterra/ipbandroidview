package com.progressterra.ipbandroidview.ui.goodsdetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class GoodsDetailsNode(
    buildContext: BuildContext,
    private val goodsId: String,
    private val onBack: () -> Unit,
    private val onAuth: () -> Unit,
    private val onNext: () -> Unit
) : com.bumble.appyx.core.node.Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: GoodsDetailsViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                GoodsDetailsEffect.Back -> onBack()
                GoodsDetailsEffect.Auth -> onAuth()
                GoodsDetailsEffect.Next -> onNext()
            }
        }
        var alreadyLaunched by rememberSaveable(goodsId) {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.setGoodsId(goodsId)
        }
        val state = viewModel.collectAsState()
        GoodsDetailsScreen(
            state = state::value,
            add = viewModel::add,
            remove = viewModel::remove,
            favorite = viewModel::favorite,
            sizeTable = viewModel::sizeTable,
            size = viewModel::size,
            back = viewModel::back,
            color = viewModel::color,
            refresh = viewModel::refresh
        )
    }
}