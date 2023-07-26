package com.progressterra.ipbandroidview.pages.bonusesdetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.entities.toScreenState
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
            reduce { BonusesDetailsState.screenState.set(state, ScreenState.LOADING) }
            var isSuccess = true
            bonusesUseCase().onSuccess {
                reduce { BonusesDetailsState.bonusesInfo.set(state, it) }
            }.onFailure {
                isSuccess = false
            }
            fetchBonusesTransactionsUseCase().onSuccess {
                reduce { BonusesDetailsState.transactions.set(state, it) }
            }.onFailure {
                isSuccess = false
            }
            reduce {
                BonusesDetailsState.screenState.set(
                    state,
                    isSuccess.toScreenState()
                )
            }
        }
    }

    override fun handle(event: BonusesEvent) = Unit

    override fun handle(event: TopBarEvent) {
        intent {
            postSideEffect(BonusesDetailsEvent.Back)
        }
    }

    override fun handle(event: StateBoxEvent) {
        intent {
            refresh()
        }
    }
}