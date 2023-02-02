package com.progressterra.ipbandroidview.ui.catalog

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.store.CatalogUseCase
import com.progressterra.ipbandroidview.model.store.CategoryWithSubcategories
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class CatalogViewModel(
    private val catalogUseCase: CatalogUseCase
) : ViewModel(), ContainerHost<CatalogState, CatalogEffect>, CatalogInteractor {

    override val container: Container<CatalogState, CatalogEffect> = container(CatalogState())

    init {
        refresh()
    }

    override fun openCategory(categoryWithSubcategories: CategoryWithSubcategories) = intent {
        if (categoryWithSubcategories.hasNext) postSideEffect(CatalogEffect.SubCatalog(categoryWithSubcategories))
        else postSideEffect(CatalogEffect.Goods(categoryWithSubcategories.id))
    }

    override fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        catalogUseCase().onSuccess {
            reduce { state.copy(categories = it, screenState = ScreenState.SUCCESS) }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }

    override fun editKeyword(keyword: String) = blockingIntent {
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