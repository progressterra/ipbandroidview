package com.progressterra.ipbandroidview.ui.catalog

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidapi.Constants
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.CatalogUseCase
import com.progressterra.ipbandroidview.domain.FilteredGoodsUseCase
import com.progressterra.ipbandroidview.domain.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.dto.NoNestedCategoriesException
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CatalogViewModel(
    private val catalogUseCase: CatalogUseCase,
    private val filteredGoodsUseCase: FilteredGoodsUseCase,
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase
) : ViewModel(), ContainerHost<CatalogState, CatalogEffect>, CatalogInteractor {

    override val container: Container<CatalogState, CatalogEffect> = container(CatalogState())

    init {
        refresh()
    }

    override fun search() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        filteredGoodsUseCase.goods(
            state.currentCategory ?: Constants.EMPTY_ID, state.keyword, state.filters
        ).onSuccess {
            reduce { state.copy(searchGoods = it, screenState = ScreenState.SUCCESS) }
        }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
    }

    override fun favorite(id: String, favorite: Boolean) = intent {
        modifyFavoriteUseCase.modifyFavorite(id, favorite)
        refresh()
    }

    override fun back() = intent {
        reduce {
            state.copy(
                filters = emptyList(), searchGoods = emptyList(), keyword = ""
            )
        }
    }

    override fun category(id: String) = intent {
        postSideEffect(CatalogEffect.SubCatalog(id))
    }

    override fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        if (state.keyword.isNotBlank()) {
            search()
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

    override fun goodsDetails(id: String) = intent {
        postSideEffect(CatalogEffect.GoodsCard(id))
    }
}