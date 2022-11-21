package com.progressterra.ipbandroidview.ui.catalog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.model.Category
import com.progressterra.ipbandroidview.ui.search.SearchEffect
import com.progressterra.ipbandroidview.ui.search.SearchViewModel
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class CatalogNode(
    buildContext: BuildContext,
    private val onGoodsDetails: (String) -> Unit,
    private val onGoods: (String) -> Unit,
    private val onSubCatalog: (Category) -> Unit,
    private val onBack: () -> Unit,
    private val onFilters: () -> Unit
) : Node(buildContext = buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: CatalogViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is CatalogEffect.Goods -> onGoods(it.categoryId)
                is CatalogEffect.SubCatalog -> onSubCatalog(it.subCategory)
            }
        }
        val searchViewModel: SearchViewModel = getViewModel()
        searchViewModel.collectSideEffect {
            when (it) {
                is SearchEffect.Back -> onBack()
                is SearchEffect.GoodsDetails -> onGoodsDetails(it.goodsId)
                is SearchEffect.Filters -> onFilters()
            }
        }
        val state = viewModel.collectAsState()
        val searchState = searchViewModel.collectAsState()
        CatalogScreen(
            catalogState = state::value,
            refresh = viewModel::refresh,
            openCategory = viewModel::openCategory,
            searchState = searchState::value,
            back = searchViewModel::back,
            openSearchGoods = searchViewModel::openDetails,
            favoriteSpecific = searchViewModel::favoriteSpecific,
            refreshSearch = searchViewModel::refresh,
            search = searchViewModel::search,
            keyword = searchViewModel::keyword,
            filters = searchViewModel::filters,
            onClear = searchViewModel::clear
        )
    }
}