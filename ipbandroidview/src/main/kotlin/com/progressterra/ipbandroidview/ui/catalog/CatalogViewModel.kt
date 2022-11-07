package com.progressterra.ipbandroidview.ui.catalog

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.CatalogUseCase
import com.progressterra.ipbandroidview.dto.NoNestedCategoriesException
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CatalogViewModel(
    private val catalogUseCase: CatalogUseCase,
    private val
    ) : ViewModel(),
    ContainerHost<CatalogState, CatalogEffect>,
    CatalogInteractor {

    override val container: Container<CatalogState, CatalogEffect> = container(CatalogState())

    init {
        refresh()
    }

    override fun favorite(id: String) = intent {

    }

    override fun openDetails(key: String) = intent {

    }

    override fun refresh() = intent {
        if (state.keyword.isNotBlank()) {

        } else {
            catalogUseCase.catalog().onSuccess {
                reduce { state.copy(categories = it, screenState = ScreenState.SUCCESS) }
            }.onFailure {
                when (it) {
                    is NoNestedCategoriesException -> reduce { state }
                }
                reduce { state.copy(screenState = ScreenState.ERROR) }
            }
        }
    }

    override fun openCard(id: String) = intent {
        postSideEffect(CatalogEffect.GoodsCard(id))
    }
}