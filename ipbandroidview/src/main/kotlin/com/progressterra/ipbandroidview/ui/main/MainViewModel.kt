package com.progressterra.ipbandroidview.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.progressterra.ipbandroidview.components.topbar.SearchBarState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.DomainConstants
import com.progressterra.ipbandroidview.domain.FilteredGoodsUseCase
import com.progressterra.ipbandroidview.domain.GoodsUseCase
import com.progressterra.ipbandroidview.domain.ModifyFavoriteUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MainViewModel(
    private val goodsUseCase: GoodsUseCase,
    private val filteredGoodsUseCase: FilteredGoodsUseCase,
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase
) : ViewModel(), ContainerHost<MainState, MainEffect>, MainInteractor {

    override val container: Container<MainState, MainEffect> = container(MainState())

    init {
        refresh()
    }

    override fun back() = intent {
        reduce {
            state.copy(
                filters = emptyList(),
                searchGoods = emptyList(),
                keyword = ""
            )
        }
    }

    override fun favorite(id: String, favorite: Boolean) = intent {
        modifyFavoriteUseCase.modifyFavorite(id, favorite)
        refresh()
    }

    override fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        goodsUseCase.goods(DomainConstants.MAIN_DEFAULT_CATEGORY_ID).onSuccess { flow ->
            reduce {
                state.copy(
                    items = flow.cachedIn(viewModelScope), screenState = ScreenState.SUCCESS
                )
            }
        }
    }

    override fun keyword(keyword: String) = intent {
        reduce { state.copy(keyword = keyword) }
        reduce { state.copy(screenState = ScreenState.LOADING) }
    }

    override fun card(id: String) = intent {
        postSideEffect(MainEffect.OpenDetails(id))
    }

    override fun search() = intent {
        filteredGoodsUseCase.goods(
            DomainConstants.MAIN_DEFAULT_CATEGORY_ID, state.keyword, state.filters
        ).onSuccess {
            reduce { state.copy(searchGoods = it, screenState = ScreenState.SUCCESS) }
        }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
    }
}