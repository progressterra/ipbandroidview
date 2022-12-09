package com.progressterra.ipbandroidview.ui.cart

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.model.store.OrderGoods
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class CartNode(
    buildContext: BuildContext,
    private val onAuth: () -> Unit,
    private val onNext: (List<OrderGoods>) -> Unit,
    private val onGoodsDetails: (String) -> Unit
) : Node(buildContext = buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: CartViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is CartEffect.Auth -> onAuth()
                is CartEffect.GoodsDetails -> onGoodsDetails(it.goodsId)
                is CartEffect.Next -> onNext(it.goods)
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.collectAsState()
        CartScreen(
            state = state::value,
            openDetails = viewModel::openDetails,
            favoriteSpecific = viewModel::favoriteSpecific,
            removeSpecific = viewModel::removeSpecific,
            next = viewModel::next,
            auth = viewModel::auth,
            refresh = viewModel::refresh
        )
    }
}