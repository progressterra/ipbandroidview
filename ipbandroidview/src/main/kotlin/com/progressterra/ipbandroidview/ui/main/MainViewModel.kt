package com.progressterra.ipbandroidview.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.DomainConstants
import com.progressterra.ipbandroidview.domain.usecase.GoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.model.StoreGoods
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@Suppress("unused", "MemberVisibilityCanBePrivate")
class MainViewModel(
    private val goodsUseCase: GoodsUseCase,
) : ViewModel(), ContainerHost<MainState, MainEffect> {

    override val container: Container<MainState, MainEffect> = container(MainState())

    init {
        refresh()
    }

    fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        goodsUseCase.goods(DomainConstants.MAIN_DEFAULT_CATEGORY_ID).onSuccess {
            reduce {
                state.copy(
                    items = it.flow.cachedIn(viewModelScope), screenState = ScreenState.SUCCESS
                )
            }
        }
    }
}