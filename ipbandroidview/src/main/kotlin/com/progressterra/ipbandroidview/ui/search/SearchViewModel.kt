package com.progressterra.ipbandroidview.ui.search

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidapi.Constants
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.store.FilteredGoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.model.StoreGoods
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@Suppress("unused", "MemberVisibilityCanBePrivate")
class SearchViewModel(
    private val filteredGoodsUseCase: FilteredGoodsUseCase,
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase
) : ViewModel(), ContainerHost<SearchState, SearchEffect> {

    override val container: Container<SearchState, SearchEffect> = container(SearchState())

    fun back() = intent {
        if (state.isEmpty())
            postSideEffect(SearchEffect.Back)
        else
            reduce { state.clear() }
    }

    fun searchInCategory(categoryId: String) = intent {
        reduce { state.copy(categoryId = categoryId) }
        refresh()
    }

    fun favoriteSpecific(item: StoreGoods) = intent {
        modifyFavoriteUseCase.modifyFavorite(item.id, item.favorite).onSuccess { refresh() }
    }

    fun refresh() = intent {
        reduce { state.copy(searchScreenState = ScreenState.LOADING) }
        filteredGoodsUseCase.goods(
            state.categoryId ?: Constants.EMPTY_ID,
            state.keyword,
            state.filters
        ).onSuccess {
            reduce { state.copy(searchGoods = it, searchScreenState = ScreenState.SUCCESS) }
        }.onFailure {
            reduce { state.copy(searchScreenState = ScreenState.ERROR) }
        }
    }

    fun keyword(keyword: String) = intent {
        reduce { state.copy(keyword = keyword) }
    }

    fun search() = intent {
        postSideEffect(SearchEffect.Search)
    }

    fun openDetails(item: StoreGoods) = intent {
        postSideEffect(SearchEffect.GoodsDetails(item.id))
    }
}