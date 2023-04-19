package com.progressterra.ipbandroidview.pages.bonuses

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.features.bonuses.BonusesEvent
import com.progressterra.ipbandroidview.features.bonuses.BonusesUseCase
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.widgets.bonusestransactions.FetchBonusesTransactionsUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class BonusesViewModel(
    private val bonusesUseCase: BonusesUseCase,
    private val fetchBonusesTransactionsUseCase: FetchBonusesTransactionsUseCase
) : ViewModel(), ContainerHost<BonusesState, BonusesEffect>, UseBonuses {

    override val container: Container<BonusesState, BonusesEffect> = container(BonusesState())

    fun refresh() = intent {
        reduce { state.updateScreenState(ScreenState.LOADING) }
        var isSuccess = true
        bonusesUseCase().onSuccess {
            reduce { state.updateBonusesInfo(it) }
        }.onFailure {
            isSuccess = false
        }
        fetchBonusesTransactionsUseCase().onSuccess {
            reduce { state.updateTransactions(it) }
        }.onFailure {
            isSuccess = false
        }
        reduce { state.updateScreenState(ScreenState.fromBoolean(isSuccess)) }
    }

    override fun handle(event: BonusesEvent) = intent {
        when (event) {
            is BonusesEvent.Action -> Unit
        }
    }

    override fun handle(event: TopBarEvent) = intent {
        when (event) {
            is TopBarEvent.Back -> postSideEffect(BonusesEffect.Back)
        }
    }

    override fun handle(event: StateBoxEvent) = intent {
        when (event) {
            is StateBoxEvent.Refresh -> refresh()
        }
    }
}