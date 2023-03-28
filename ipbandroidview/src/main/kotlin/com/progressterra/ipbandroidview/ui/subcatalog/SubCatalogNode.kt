package com.progressterra.ipbandroidview.ui.subcatalog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.entities.CategoryWithSubcategories
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class SubCatalogNode(
    buildContext: BuildContext,
    private val subCategoryWithSubcategories: CategoryWithSubcategories,
    private val onSubCategory: (CategoryWithSubcategories) -> Unit,
    private val onGoods: (String) -> Unit,
    private val onBack: () -> Unit,
    private val onSearch: (String, String) -> Unit
) : Node(buildContext) {


    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: SubCatalogViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is SubCatalogEffect.Goods -> onGoods(it.categoryId)
                is SubCatalogEffect.SubCatalog -> onSubCategory(it.subCategoryWithSubcategories)
                is SubCatalogEffect.Back -> onBack()
                is SubCatalogEffect.Search -> onSearch(subCategoryWithSubcategories.id, it.keyword)
            }
        }
        var alreadyLaunched by rememberSaveable(subCategoryWithSubcategories) {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.setSubCategory(subCategoryWithSubcategories)
        }
        val state = viewModel.collectAsState()
        SubCatalogScreen(
            state = state.value,
//            interactor = viewModel
        )
    }
}