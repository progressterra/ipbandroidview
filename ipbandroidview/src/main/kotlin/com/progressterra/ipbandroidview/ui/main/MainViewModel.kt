package com.progressterra.ipbandroidview.ui.main

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.composable.component.ExtendedBonusesEvent
import com.progressterra.ipbandroidview.composable.component.StoreCardComponentState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.bonus.AvailableBonusesUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.CreateQrUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.PromoGoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.UserExistsUseCase
import com.progressterra.ipbandroidview.ext.toScreenState
import com.progressterra.ipbandroidview.model.Category
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MainViewModel(
    private val promoGoodsUseCase: PromoGoodsUseCase,
    private val availableBonusesUseCase: AvailableBonusesUseCase,
    private val userExistsUseCase: UserExistsUseCase,
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase,
    private val createQrUseCase: CreateQrUseCase
) : ViewModel(), ContainerHost<MainState, MainEffect>, MainInteractor {

    override val container: Container<MainState, MainEffect> = container(MainState())

    init {
        refresh()
    }

    override fun onCategory(category: Category) = intent {
        postSideEffect(MainEffect.Goods(category.id))
    }

    override fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        var isSuccess = true
        createQrUseCase().onSuccess { qr ->
            reduce { state.copy(qr = qr) }
        }.onFailure { isSuccess = false }
        availableBonusesUseCase().onSuccess { bonusesInfo ->
            reduce { state.copy(extendedBonusesState = bonusesInfo) }
        }.onFailure { isSuccess = false }
        promoGoodsUseCase().onSuccess {
            reduce { state.copy(recommended = it) }
        }.onFailure { isSuccess = false }
        val userExist = userExistsUseCase()
        reduce { state.copy(screenState = isSuccess.toScreenState(), userExist = userExist) }
    }

    override fun onClick(storeCard: StoreCardComponentState) = intent {
        postSideEffect(MainEffect.GoodsDetails(storeCard.id))
    }

    override fun favorite(storeCard: StoreCardComponentState) = intent {
        modifyFavoriteUseCase(storeCard.id, storeCard.favorite).onSuccess { refresh() }
    }

    override fun handleEvent(id: String, event: ExtendedBonusesEvent) = intent {
        when (id) {
            "main" -> when (event) {
                is ExtendedBonusesEvent.OnClick -> postSideEffect(MainEffect.Bonuses)
                is ExtendedBonusesEvent.SpendBonuses -> postSideEffect(MainEffect.Spend)
                is ExtendedBonusesEvent.InviteFriends -> postSideEffect(MainEffect.Invite)
            }
        }
    }
}