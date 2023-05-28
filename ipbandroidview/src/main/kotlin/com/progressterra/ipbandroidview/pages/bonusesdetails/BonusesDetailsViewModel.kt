package com.progressterra.ipbandroidview.pages.bonusesdetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.bonuses.BonusesEvent
import com.progressterra.ipbandroidview.features.bonuses.BonusesUseCase
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.widgets.bonusestransactions.FetchBonusesTransactionsUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class BonusesDetailsViewModel(
    private val bonusesUseCase: BonusesUseCase,
    private val fetchBonusesTransactionsUseCase: FetchBonusesTransactionsUseCase
) : ViewModel(), ContainerHost<BonusesDetailsState, BonusesDetailsEvent>, UseBonusesDetails {

    override val container: Container<BonusesDetailsState, BonusesDetailsEvent> =
        container(BonusesDetailsState())

    fun refresh() {
        intent {
            reduce { state.uScreenState(ScreenState.LOADING) }
            var isSuccess = true
            bonusesUseCase().onSuccess {
                reduce { state.uBonusesInfo(it) }
            }.onFailure {
                isSuccess = false
            }
            fetchBonusesTransactionsUseCase().onSuccess {
                reduce { state.uTransactions(it) }
            }.onFailure {
                isSuccess = false
            }
            reduce { state.uScreenState(ScreenState.fromBoolean(isSuccess)) }
        }
    }

    override fun handle(event: BonusesEvent) {
        intent {
            when (event) {
                is BonusesEvent.Action -> Unit
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            when (event) {
                is TopBarEvent.Back -> postSideEffect(BonusesDetailsEvent.Back)
            }
        }
    }

    override fun handle(event: StateBoxEvent) {
        intent {
            when (event) {
                is StateBoxEvent.Refresh -> refresh()
            }
        }
    }
}