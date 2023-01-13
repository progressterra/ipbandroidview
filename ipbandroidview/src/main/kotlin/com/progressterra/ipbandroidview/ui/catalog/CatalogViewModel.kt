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
) : ViewModel(), ContainerHost<CatalogState, CatalogEffect>, CatalogInteractor {

    override val container: Container<CatalogState, CatalogEffect> = container(CatalogState())

    init {
        refresh()
    }

    override fun openCategory(category: Category) = intent {
        if (category.hasNext) postSideEffect(CatalogEffect.SubCatalog(category))
        else postSideEffect(CatalogEffect.Goods(category.id))
    }

    override fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        catalogUseCase().onSuccess {
            reduce { state.copy(categories = it, screenState = ScreenState.SUCCESS) }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }

    override fun editKeyword(keyword: String) = intent {
        reduce { state.copy(keyword = keyword) }
    }

    override fun search() = intent {
        postSideEffect(CatalogEffect.Search(state.keyword))
        clear()
    }

    override fun clear() = intent {
        reduce { state.copy(keyword = "") }
    }
}