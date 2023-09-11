package com.progressterra.ipbandroidview.pages.bonusesdetails

import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.bonuses.BonusesEvent
import com.progressterra.ipbandroidview.features.bonuses.BonusesUseCase
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.widgets.bonusestransactions.FetchBonusesTransactionsUseCase

class BonusesDetailsScreenViewModel(
    private val bonusesUseCase: BonusesUseCase,
    private val fetchBonusesTransactionsUseCase: FetchBonusesTransactionsUseCase
) : AbstractNonInputViewModel<BonusesDetailsScreenState, BonusesDetailsScreenEffect>(),
    UseBonusesDetailsScreen {

    override fun createInitialState() = BonusesDetailsScreenState()

    override fun refresh() {
        onBackground {
            emitState { createInitialState() }
            var isSuccess = true
            bonusesUseCase().onSuccess { bonusesInfo ->
                emitState {
                    it.copy(bonusesInfo = bonusesInfo)
                }
            }.onFailure {
                isSuccess = false
            }
            fetchBonusesTransactionsUseCase().onSuccess { transactions ->
                emitState {
                    it.copy(transactions = transactions)
                }
            }.onFailure {
                isSuccess = false
            }
            emitState {
                it.copy(screen = it.screen.copy(state = isSuccess.toScreenState()))
            }
        }
    }

    override fun handle(event: BonusesEvent) = Unit

    override fun handle(event: TopBarEvent) {
        postEffect(BonusesDetailsScreenEffect.Back)
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}