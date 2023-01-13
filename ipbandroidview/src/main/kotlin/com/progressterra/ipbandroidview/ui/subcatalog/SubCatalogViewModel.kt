package com.progressterra.ipbandroidview.ui.subcatalog

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.model.store.Category
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class SubCatalogViewModel : ViewModel(), ContainerHost<SubCatalogState, SubCatalogEffect>,
    SubCatalogInteractor {

    override val container: Container<SubCatalogState, SubCatalogEffect> =
        container(SubCatalogState())

    fun setSubCategory(subCategory: Category) = intent {
        reduce { state.copy(currentCategory = subCategory) }
    }

    override fun onBack() = intent {
        postSideEffect(SubCatalogEffect.Back)
    }

    override fun onSubCategory(category: Category) = blockingIntent {
        if (category.hasNext)
            postSideEffect(SubCatalogEffect.SubCatalog(category))
        else
            postSideEffect(SubCatalogEffect.Goods(category.id))
    }

    override fun editKeyword(keyword: String) = intent {
        reduce { state.copy(keyword = keyword) }
    }

    override fun search() = intent {
        postSideEffect(SubCatalogEffect.Search(state.keyword))
        clearSearch()
    }

    override fun clearSearch() = intent {
        reduce { state.copy(keyword = "", expanded = false) }
    }

    override fun expandSearch() = intent {
        reduce { state.copy(expanded = true) }
    }
}