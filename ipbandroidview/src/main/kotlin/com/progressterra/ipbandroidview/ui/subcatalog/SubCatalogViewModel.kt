package com.progressterra.ipbandroidview.ui.subcatalog

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.model.store.Category
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SubCatalogViewModel : ViewModel(), ContainerHost<SubCatalogState, SubCatalogEffect> {

    override val container: Container<SubCatalogState, SubCatalogEffect> =
        container(SubCatalogState())

    fun setSubCategory(subCategory: Category) = intent {
        reduce { state.copy(currentCategory = subCategory) }
    }

    fun back() = intent {
        postSideEffect(SubCatalogEffect.Back)
    }

    fun subCategory(subCategory: Category) = intent {
        if (subCategory.hasNext)
            postSideEffect(SubCatalogEffect.SubCatalog(subCategory))
        else
            postSideEffect(SubCatalogEffect.Goods(subCategory.id))
    }

    fun keyword(keyword: String) = intent {
        reduce { state.copy(keyword = keyword) }
    }

    fun search() = intent {
        postSideEffect(SubCatalogEffect.Search(state.keyword))
        clear()
    }

    fun clear() = intent {
        reduce { state.copy(keyword = "", expanded = false) }
    }

    fun expand() = intent {
        reduce { state.copy(expanded = true) }
    }
}