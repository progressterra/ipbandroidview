package com.progressterra.ipbandroidview.ui.catalog

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.CatalogUseCase
import com.progressterra.ipbandroidview.model.Category
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@Suppress("unused", "MemberVisibilityCanBePrivate")
class CatalogViewModel(
    private val catalogUseCase: CatalogUseCase
) : ViewModel(), ContainerHost<CatalogState, CatalogEffect> {

    override val container: Container<CatalogState, CatalogEffect> = container(CatalogState())

    init {
        refresh()
    }

    fun openCategory(item: Category) = intent {
        if (item.hasNext)
            postSideEffect(CatalogEffect.SubCatalog(item))
        else
            postSideEffect(CatalogEffect.Goods(item.id))
    }

    fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        catalogUseCase.catalog().onSuccess {
            reduce { state.copy(categories = it, screenState = ScreenState.SUCCESS) }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }
}