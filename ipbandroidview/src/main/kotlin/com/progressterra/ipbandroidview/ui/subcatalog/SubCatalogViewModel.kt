package com.progressterra.ipbandroidview.ui.subcatalog

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.entities.CategoryWithSubcategories
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class SubCatalogViewModel : ViewModel(), ContainerHost<SubCatalogState, SubCatalogEffect> {

    override val container: Container<SubCatalogState, SubCatalogEffect> =
        container(SubCatalogState())

    fun setSubCategory(subCategoryWithSubcategories: CategoryWithSubcategories) = intent {
        reduce { state.copy(currentCategoryWithSubcategories = subCategoryWithSubcategories) }
    }

//    override fun onBack() = intent {
//        postSideEffect(SubCatalogEffect.Back)
//    }
//
//    override fun onSubCategory(categoryWithSubcategories: CategoryWithSubcategories) = intent {
//        if (categoryWithSubcategories.hasNext)
//            postSideEffect(SubCatalogEffect.SubCatalog(categoryWithSubcategories))
//        else
//            postSideEffect(SubCatalogEffect.Goods(categoryWithSubcategories.id))
//    }

//    override fun editKeyword(keyword: String) = blockingIntent {
//        reduce { state.copy(keyword = keyword) }
//    }
//
//    override fun search() = intent {
//        postSideEffect(SubCatalogEffect.Search(state.keyword))
//        clearSearch()
//    }
//
//    override fun clearSearch() = intent {
//        reduce { state.copy(keyword = "", expanded = false) }
//    }
//
//    override fun expandSearch() = intent {
//        reduce { state.copy(expanded = true) }
//    }
}