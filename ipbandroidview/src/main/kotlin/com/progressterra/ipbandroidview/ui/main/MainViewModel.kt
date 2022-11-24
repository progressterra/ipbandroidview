package com.progressterra.ipbandroidview.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.DomainConstants
import com.progressterra.ipbandroidview.domain.usecase.UserExistUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.AvailableBonusesUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.GoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.model.StoreGoods
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MainViewModel(
    private val goodsUseCase: GoodsUseCase,
    private val availableBonusesUseCase: AvailableBonusesUseCase,
    private val userExistUseCase: UserExistUseCase,
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase
) : ViewModel(), ContainerHost<MainState, MainEffect> {

    override val container: Container<MainState, MainEffect> = container(MainState())

    init {
        refresh()
    }

    fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        availableBonusesUseCase.availableBonuses().onSuccess {
            reduce { state.copy(bonuses = it) }
            goodsUseCase.goods(DomainConstants.MAIN_DEFAULT_CATEGORY_ID).onSuccess {
                reduce { state.copy(items = it.flow.cachedIn(viewModelScope)) }
                userExistUseCase.userExist().onSuccess {
                    reduce { state.copy(userExist = it, screenState = ScreenState.SUCCESS) }
                }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
            }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
        }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
    }

    fun favoriteSpecific(item: StoreGoods) = intent {
        modifyFavoriteUseCase.modifyFavorite(item.id, item.favorite).onSuccess { refresh() }
    }

    fun openDetails(item: StoreGoods) = intent {
        postSideEffect(MainEffect.GoodsDetails(item.id))
    }

    fun bonuses() = intent {
        postSideEffect(MainEffect.Bonuses)
    }

    fun keyword(keyword: String) = intent {
        reduce { state.copy(keyword = keyword) }
    }

    fun search() = intent {
        postSideEffect(MainEffect.Search(state.keyword))
    }

    fun clear() = intent {
        reduce { state.copy(keyword = "") }
    }
}