package com.progressterra.ipbandroidview.ui.goods

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.ui.search.SearchEffect
import com.progressterra.ipbandroidview.ui.search.SearchViewModel
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class GoodsNode(
    buildContext: BuildContext,
    private val categoryId: String,
    private val onGoodsDetails: (String) -> Unit,
    private val onBack: () -> Unit,
    private val onFilters: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val searchViewModel: SearchViewModel = getViewModel()
        val viewModel: GoodsViewModel = getViewModel()
        searchViewModel.collectSideEffect {
            when (it) {
                is SearchEffect.GoodsDetails -> onGoodsDetails(it.goodsId)
                is SearchEffect.Back -> onBack()
                is SearchEffect.Filters -> onFilters()
            }
        }
        var alreadyLaunched by rememberSaveable(categoryId) {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.setCategoryId(categoryId)
        }
        val searchState = searchViewModel.collectAsState()
        val state = viewModel.collectAsState()
        GoodsScreen(
            goodsState = state::value,
            searchState = searchState::value,
            refresh = viewModel::refresh,
            back = searchViewModel::back,
            filters = searchViewModel::filters,
            favoriteSpecific = searchViewModel::favoriteSpecific,
            searchRefresh = searchViewModel::refresh,
            openDetails = searchViewModel::openDetails,
            keyword = searchViewModel::keyword,
            search = searchViewModel::search,
            clear = searchViewModel::clear
        )
    }
}