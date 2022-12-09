package com.progressterra.ipbandroidview.ui.catalog

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.store.CatalogUseCase
import com.progressterra.ipbandroidview.model.store.Category
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CatalogViewModel(
    private val catalogUseCase: CatalogUseCase
) : ViewModel(), ContainerHost<CatalogState, CatalogEffect> {

    override val container: Container<CatalogState, CatalogEffect> = container(CatalogState())

    init {
        refresh()
    }

    fun openCategory(item: Category) = intent {
        if (item.hasNext) postSideEffect(CatalogEffect.SubCatalog(item))
        else postSideEffect(CatalogEffect.Goods(item.id))
    }

    fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        catalogUseCase().onSuccess {
            reduce { state.copy(categories = it, screenState = ScreenState.SUCCESS) }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }

    fun keyword(keyword: String) = intent {
        reduce { state.copy(keyword = keyword) }
    }

    fun search() = intent {
        postSideEffect(CatalogEffect.Search(state.keyword))
        clear()
    }

    fun clear() = intent {
        reduce { state.copy(keyword = "") }
    }
}