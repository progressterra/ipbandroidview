package com.progressterra.ipbandroidview.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.AppSettings
import com.progressterra.ipbandroidview.domain.usecase.NotificationUseCase
import com.progressterra.ipbandroidview.domain.usecase.bonus.AvailableBonusesUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.GoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.UserExistsUseCase
import com.progressterra.ipbandroidview.model.store.StoreGoods
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MainViewModel(
    private val goodsUseCase: GoodsUseCase,
    private val availableBonusesUseCase: AvailableBonusesUseCase,
    private val userExistsUseCase: UserExistsUseCase,
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase,
    private val notificationUseCase: NotificationUseCase
) : ViewModel(), ContainerHost<MainState, MainEffect> {

    override val container: Container<MainState, MainEffect> = container(MainState())

    init {
        refresh()
    }

    fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        notificationUseCase().onSuccess { notifications ->
            reduce { state.copy(notifications = notifications) }
            availableBonusesUseCase().onSuccess { bonusesInfo ->
                reduce { state.copy(bonuses = bonusesInfo) }
                goodsUseCase(AppSettings.MAIN_DEFAULT_CATEGORY_ID).onSuccess { pager ->
                    reduce { state.copy(items = pager.flow.cachedIn(viewModelScope)) }
                    userExistsUseCase().onSuccess {
                        reduce { state.copy(userExist = it, screenState = ScreenState.SUCCESS) }
                    }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
                }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
            }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
        }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
    }

    fun favoriteSpecific(item: StoreGoods) = intent {
        modifyFavoriteUseCase(item.id, item.favorite).onSuccess { refresh() }
    }

    fun openDetails(item: StoreGoods) = intent {
        postSideEffect(MainEffect.GoodsDetails(item.id))
    }

    fun bonuses() = intent {
        postSideEffect(MainEffect.Bonuses)
    }
}