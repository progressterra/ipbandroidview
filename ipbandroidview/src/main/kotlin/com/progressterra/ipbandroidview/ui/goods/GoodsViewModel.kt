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
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class GoodsViewModel(
    private val goodsUseCase: GoodsUseCase,
    private val filteredGoodsUseCase: FilteredGoodsUseCase,
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase
) : ViewModel(), ContainerHost<GoodsState, GoodsEffect>, GoodsInteractor {

    override val container: Container<GoodsState, GoodsEffect> = container(GoodsState())

    fun setup(categoryId: String, keyword: String?) = intent {
        reduce { state.copy(currentCategory = categoryId) }
        keyword?.let { reduce { state.copy(keyword = it) } }
        refresh()
    }

    override fun favoriteSpecific(goods: StoreGoods) = intent {
        modifyFavoriteUseCase(goods.id, goods.favorite).onSuccess { refresh() }
    }

    override fun openDetails(goods: StoreGoods) = intent {
        postSideEffect(GoodsEffect.GoodsDetails(goods.id))
    }

    override fun onBack() = intent {
        postSideEffect(GoodsEffect.Back)
    }

    override fun filters() = intent {
        postSideEffect(GoodsEffect.Filters)
    }

    override fun editKeyword(keyword: String) = blockingIntent {
        reduce { state.copy(keyword = keyword) }
    }

    override fun search() = intent {
        if (state.keyword.isNotBlank())
            refreshSearch()
    }

    override fun clear() = intent {
        reduce { state.copy(keyword = "") }
    }

    override fun refresh() = intent {
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