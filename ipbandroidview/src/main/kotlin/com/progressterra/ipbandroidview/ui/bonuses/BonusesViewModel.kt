package com.progressterra.ipbandroidview.ui.bonuses

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.store.AvailableBonusesUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.TransactionsUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class BonusesViewModel(
    private val availableBonusesUseCase: AvailableBonusesUseCase,
    private val transactionsUseCase: TransactionsUseCase
) : ViewModel(), ContainerHost<BonusesState, BonusesEffect> {

    init {
        refresh()
    }

    override val container: Container<BonusesState, BonusesEffect> = container(BonusesState())

    fun clarification() = intent {
        postSideEffect(BonusesEffect.Clarification)
    }

    fun back() = intent {
        postSideEffect(BonusesEffect.Back)
    }

    fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        availableBonusesUseCase.availableBonuses().onSuccess {
            reduce { state.copy(bonusesInfo = it) }
            transactionsUseCase.transactions().onSuccess {
                reduce { state.copy(transactions = it, screenState = ScreenState.SUCCESS) }
            }.onFailure {
                reduce { state.copy(screenState = ScreenState.ERROR) }
            }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }
}