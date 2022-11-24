package com.progressterra.ipbandroidview.ui.catalog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidapi.Constants
import com.progressterra.ipbandroidview.model.Category
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class CatalogNode(
    buildContext: BuildContext,
    private val onGoods: (String) -> Unit,
    private val onSubCatalog: (Category) -> Unit,
    private val onSearch: (String, String) -> Unit
) : Node(buildContext = buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: CatalogViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is CatalogEffect.Goods -> onGoods(it.categoryId)
                is CatalogEffect.SubCatalog -> onSubCatalog(it.subCategory)
                is CatalogEffect.Search -> onSearch(Constants.EMPTY_ID, it.keyword)
            }
        }
        val state = viewModel.collectAsState()
        CatalogScreen(
            state = state::value,
            refresh = viewModel::refresh,
            openCategory = viewModel::openCategory,
            search = viewModel::search,
            keyword = viewModel::keyword,
            onClear = viewModel::clear
        )
    }
}