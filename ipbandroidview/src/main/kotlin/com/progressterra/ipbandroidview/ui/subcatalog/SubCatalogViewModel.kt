package com.progressterra.ipbandroidview.ui.subcatalog

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.dto.SubCategory
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

    init {
        refresh()
    }

    fun setSubCategory(subCategory: SubCategory) = intent {
        reduce { state.copy(currentCategory = subCategory) }
    }

    override fun back() = intent {
        postSideEffect(SubCatalogEffect.Back)
    }

    override fun favorite(id: String, favorite: Boolean) {

    }

    override fun refresh() {

    }

    override fun search() {

    }

    override fun goodsDetails(id: String) = intent {
        postSideEffect(SubCatalogEffect.GoodsCard(id))
    }

    override fun subCategory(id: String) = intent {
        postSideEffect(SubCatalogEffect.SubCatalog(id))

    }
}