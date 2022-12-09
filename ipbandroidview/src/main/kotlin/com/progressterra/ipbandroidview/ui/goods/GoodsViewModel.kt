package com.progressterra.ipbandroidview.ui.goods

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.store.FilteredGoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.GoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.model.store.StoreGoods
import kotlinx.coroutines.flow.emptyFlow
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class GoodsViewModel(
    private val goodsUseCase: GoodsUseCase,
    private val filteredGoodsUseCase: FilteredGoodsUseCase,
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase
) : ViewModel(), ContainerHost<GoodsState, GoodsEffect> {

    override val container: Container<GoodsState, GoodsEffect> = container(GoodsState())

    fun setup(categoryId: String, keyword: String?) = intent {
        reduce { state.copy(currentCategory = categoryId) }
        keyword?.let { reduce { state.copy(keyword = it) } }
        refresh()
    }

    fun favoriteSpecific(item: StoreGoods) = intent {
        modifyFavoriteUseCase(item.id, item.favorite).onSuccess { refresh() }
    }

    fun openDetails(item: StoreGoods) = intent {
        postSideEffect(GoodsEffect.GoodsDetails(item.id))
    }

    fun back() = intent {
        postSideEffect(GoodsEffect.Back)
    }

    fun filters() = intent {
        postSideEffect(GoodsEffect.Filters)
    }

    fun keyword(keyword: String) = intent {
        reduce { state.copy(keyword = keyword) }
    }

    fun search() = intent {
        if (state.keyword.isNotBlank())
            refreshSearch()
    }

    fun clear() = intent {
        reduce { state.copy(keyword = "") }
    }

    fun refresh() = intent {
        if (state.keyword.isNotBlank())
            refreshSearch()
        else
            refreshCategory()
    }

    private fun refreshSearch() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        filteredGoodsUseCase(
            state.currentCategory,
            state.keyword,
            state.filters
        ).onSuccess {
            reduce {
                state.copy(
                    items = it,
                    itemsFlow = emptyFlow(),
                    screenState = ScreenState.SUCCESS
                )
            }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }

    private fun refreshCategory() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        goodsUseCase(state.currentCategory).onSuccess {
            reduce {
                state.copy(
                    itemsFlow = it.flow.cachedIn(viewModelScope),
                    items = emptyList(),
                    screenState = ScreenState.SUCCESS
                )
            }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }
}