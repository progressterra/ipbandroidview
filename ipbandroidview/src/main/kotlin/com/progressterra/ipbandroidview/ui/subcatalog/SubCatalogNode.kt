package com.progressterra.ipbandroidview.ui.subcatalog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.model.Category
import com.progressterra.ipbandroidview.ui.search.SearchEffect
import com.progressterra.ipbandroidview.ui.search.SearchViewModel
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class SubCatalogNode(
    buildContext: BuildContext,
    private val subCategory: Category,
    private val onSubCategory: (Category) -> Unit,
    private val onGoodsDetails: (String) -> Unit,
    private val onGoods: (String) -> Unit,
    private val onBack: () -> Unit,
    private val onFilters: () -> Unit
) : Node(buildContext) {


    @Composable
    override fun View(modifier: Modifier) {
        val searchViewModel: SearchViewModel = getViewModel()
        val viewModel: SubCatalogViewModel = getViewModel()
        searchViewModel.collectSideEffect {
            when (it) {
                is SearchEffect.GoodsDetails -> onGoodsDetails(it.goodsId)
                is SearchEffect.Back -> onBack()
                is SearchEffect.Filters -> onFilters
            }
        }
        viewModel.collectSideEffect {
            when (it) {
                is SubCatalogEffect.Goods -> onGoods(it.categoryId)
                is SubCatalogEffect.SubCatalog -> onSubCategory(it.subCategory)
            }
        }
        var alreadyLaunched by rememberSaveable(subCategory) {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.setSubCategory(subCategory)
        }
        val searchState = searchViewModel.collectAsState()
        val state = viewModel.collectAsState()
        SubCatalogScreen(
            subCatalogState = state::value,
            searchState = searchState::value,
            back = searchViewModel::back,
            subCategory = viewModel::subCategory,
            filters = searchViewModel::filters,
            favoriteSpecific = searchViewModel::favoriteSpecific,
            searchRefresh = searchViewModel::refresh,
            openDetails = searchViewModel::openDetails,
            keyword = searchViewModel::keyword,
            search = searchViewModel::search,
            onClear = searchViewModel::clear
        )
    }
}