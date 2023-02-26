package com.progressterra.ipbandroidview.ui.goods

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
class GoodsNode(
    buildContext: BuildContext,
    private val categoryId: String,
    private val keyword: String? = null,
    private val onGoodsDetails: (String) -> Unit,
    private val onBack: () -> Unit,
    private val onFilters: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: GoodsViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is GoodsEffect.Back -> onBack()
                is GoodsEffect.Filters -> onFilters()
                is GoodsEffect.GoodsDetails -> onGoodsDetails(it.goodsId)
            }
        }
        var alreadyLaunched by rememberSaveable(categoryId, keyword) {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
//            viewModel.setup(categoryId, keyword)
        }
        val state = viewModel.collectAsState()
        GoodsScreen(
            state = state.value,
            interactor = viewModel
        )
    }
}