package com.progressterra.ipbandroidview.ui.catalog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.data.Constants
import com.progressterra.ipbandroidview.model.store.CategoryWithSubcategories
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class CatalogNode(
    buildContext: BuildContext,
    private val onGoods: (String) -> Unit,
    private val onSubCatalog: (CategoryWithSubcategories) -> Unit,
    private val onSearch: (String, String) -> Unit
) : Node(buildContext = buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: CatalogViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is CatalogEffect.Goods -> onGoods(it.categoryId)
                is CatalogEffect.SubCatalog -> onSubCatalog(it.subCategoryWithSubcategories)
                is CatalogEffect.Search -> onSearch(Constants.DEFAULT_ID, it.keyword)
            }
        }
        val state = viewModel.collectAsState()
        CatalogScreen(
            state = state.value,
            interactor = viewModel
        )
    }
}