package com.progressterra.ipbandroidview.ui.search

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.FilteredGoodsUseCase
import com.progressterra.ipbandroidview.domain.ModifyFavoriteUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SearchViewModel(
    private val filteredGoodsUseCase: FilteredGoodsUseCase,
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase
) : ViewModel(), ContainerHost<SearchState, SearchEffect>, SearchInteractor {

    override val container: Container<SearchState, SearchEffect> = container(SearchState())

    override fun back() = intent {
        reduce {
            state.copy(
                visible = false,
                filters = emptyList(),
                keyword = "",
                searchScreenState = ScreenState.LOADING,
                searchGoods = emptyList()
            )
        }
    }

    override fun favorite(id: String, favorite: Boolean) = intent {
        modifyFavoriteUseCase.modifyFavorite(id, favorite).onSuccess { refresh() }
    }

    override fun refresh() = intent {
        reduce { state.copy(searchScreenState = ScreenState.LOADING) }
        filteredGoodsUseCase.goods(state.categoryId, state.keyword, state.filters).onSuccess {
            reduce { state.copy(searchGoods = it, searchScreenState = ScreenState.SUCCESS) }
        }.onFailure {
            reduce { state.copy(searchScreenState = ScreenState.ERROR) }
        }
    }

    override fun keyword(keyword: String) = intent {
        reduce { state.copy(keyword = keyword) }
    }

    override fun search() = intent {
        refresh()
    }
}