package com.progressterra.ipbandroidview.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.DomainConstants
import com.progressterra.ipbandroidview.domain.usecase.UserExistUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.AvailableBonusesUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.GoodsUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MainViewModel(
    private val goodsUseCase: GoodsUseCase,
    private val availableBonusesUseCase: AvailableBonusesUseCase,
    private val userExistUseCase: UserExistUseCase
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
                reduce {
                    state.copy(
                        items = it.flow.cachedIn(viewModelScope), screenState = ScreenState.SUCCESS
                    )
                }
                val userExist = userExistUseCase.userExist()
                reduce { state.copy(userExist = userExist) }
            }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
        }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
    }

    fun bonuses() = intent {
        postSideEffect(MainEffect.Bonuses)
    }
}