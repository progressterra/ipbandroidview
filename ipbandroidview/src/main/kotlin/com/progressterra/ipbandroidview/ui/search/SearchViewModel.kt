package com.progressterra.ipbandroidview.ui.search

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidapi.Constants
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.FilteredGoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.model.Goods
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SearchViewModel(
    private val filteredGoodsUseCase: FilteredGoodsUseCase,
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase
) : ViewModel(), ContainerHost<SearchState, SearchEffect>, SearchInteractor {

    override val container: Container<SearchState, SearchEffect> = container(SearchState())

    override fun back() = intent {
        if (state.isEmpty())
            postSideEffect(SearchEffect.Back)
        else
            reduce { state.clear() }
    }

    @Suppress("unused")
    fun searchInCategory(categoryId: String) = intent {
        reduce { state.copy(categoryId = categoryId) }
        refresh()
    }

    override fun favorite(goodsId: String, favorite: Boolean) = intent {
        modifyFavoriteUseCase.modifyFavorite(goodsId, favorite).onSuccess { refresh() }
    }

    override fun refresh() = intent {
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

    override fun keyword(keyword: String) = intent {
        reduce { state.copy(keyword = keyword) }
    }

    override fun search() = intent {
        postSideEffect(SearchEffect.Search)
    }

    override fun goodsDetails(goods: Goods) = intent {
        postSideEffect(SearchEffect.GoodsDetails(goods))
    }
}