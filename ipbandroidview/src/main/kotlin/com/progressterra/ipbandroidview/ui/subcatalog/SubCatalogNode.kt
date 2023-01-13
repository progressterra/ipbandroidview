package com.progressterra.ipbandroidview.ui.subcatalog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.model.store.Category
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class SubCatalogNode(
    buildContext: BuildContext,
    private val subCategory: Category,
    private val onSubCategory: (Category) -> Unit,
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
                is SubCatalogEffect.SubCatalog -> onSubCategory(it.subCategory)
                is SubCatalogEffect.Back -> onBack()
                is SubCatalogEffect.Search -> onSearch(subCategory.id, it.keyword)
            }
        }
        var alreadyLaunched by rememberSaveable(subCategory) {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.setSubCategory(subCategory)
        }
        val state = viewModel.collectAsState()
        SubCatalogScreen(
            state = state.value,
            interactor = viewModel
        )
    }
}