package com.progressterra.ipbandroidview.ui.subcatalog

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.model.SubCategory
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SubCatalogViewModel : ViewModel(), ContainerHost<SubCatalogState, SubCatalogEffect>,
    SubCatalogInteractor {

    override val container: Container<SubCatalogState, SubCatalogEffect> =
        container(SubCatalogState())

    @Suppress("unused")
    fun setSubCategory(subCategory: SubCategory) = intent {
        reduce { state.copy(currentCategory = subCategory) }
    }

    override fun back() = intent {
        postSideEffect(SubCatalogEffect.Back)
    }

    override fun subCategory(subCategory: SubCategory) = intent {
        if (subCategory.hasNext)
            postSideEffect(SubCatalogEffect.SubCatalog(subCategory))
        else
            postSideEffect(SubCatalogEffect.Goods(subCategory.id))
    }
}