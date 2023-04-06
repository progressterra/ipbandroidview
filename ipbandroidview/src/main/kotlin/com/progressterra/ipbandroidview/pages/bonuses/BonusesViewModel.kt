package com.progressterra.ipbandroidview.pages.bonuses

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.features.proshkabonuses.ProshkaBonusesEvent
import com.progressterra.ipbandroidview.features.proshkabonuses.ProshkaBonusesUseCase
import com.progressterra.ipbandroidview.features.proshkatopbar.ProshkaTopBarEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.widgets.bonusestransactions.FetchBonusesTransactionsUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class BonusesViewModel(
    private val proshkaBonusesUseCase: ProshkaBonusesUseCase,
    private val fetchBonusesTransactionsUseCase: FetchBonusesTransactionsUseCase
) : ViewModel(), ContainerHost<BonusesState, BonusesEffect>, UseBonuses {

    override val container: Container<BonusesState, BonusesEffect> = container(BonusesState())

    fun refresh() = intent {
        reduce { state.updateStateBoxState(ScreenState.LOADING) }
        var isSuccess = true
        proshkaBonusesUseCase().onSuccess {
            reduce { state.updateBonusesInfo(it) }
        }.onFailure {
            isSuccess = false
        }
        fetchBonusesTransactionsUseCase().onSuccess {
            reduce { state.updateTransactions(it) }
        }.onFailure {
            isSuccess = false
        }
        reduce { state.updateStateBoxState(ScreenState.fromBoolean(isSuccess)) }
    }

    override fun handle(event: ProshkaBonusesEvent) = intent {
        when (event) {
            is ProshkaBonusesEvent.Action -> Unit
            is ProshkaBonusesEvent.Reverse -> reduce { state.reverse() }
        }
    }

    override fun handle(event: ProshkaTopBarEvent) = intent {
        when (event) {
            is ProshkaTopBarEvent.Back -> postSideEffect(BonusesEffect.Back)
        }
    }

    override fun handle(event: StateBoxEvent) = intent {
        when (event) {
            is StateBoxEvent.Refresh -> refresh()
        }
    }
}