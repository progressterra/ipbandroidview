package com.progressterra.ipbandroidview.pages.occupacion

import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.occupacion.FetchOccupationsUseCase
import com.progressterra.ipbandroidview.processes.occupacion.FetchUserOccupationUseCase
import com.progressterra.ipbandroidview.processes.occupacion.SaveOccupationUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class OccupationScreenViewModel(
    private val saveOccupationUseCase: SaveOccupationUseCase,
    private val fetchOccupationsUseCase: FetchOccupationsUseCase,
    private val fetchUserOccupationUseCase: FetchUserOccupationUseCase
) :
    AbstractNonInputViewModel<OccupationScreenState, OccupationScreenEffect>(),
    UseOccupationScreen {

    override fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            var isSuccess = true
            fetchOccupationsUseCase().onSuccess { newOccupations ->
                emitState {
                    it.copy(allOccupations = newOccupations)
                }
            }.onFailure { isSuccess = false }
            fetchUserOccupationUseCase().onSuccess { newOccupation ->
                emitState {
                    it.copy(currentOccupation = newOccupation, save = it.save.copy(enabled = newOccupation != null))
                }
            }.onFailure { isSuccess = false }
            emitState { it.copy(screen = it.screen.copy(state = isSuccess.toScreenState())) }
        }
    }

    override fun createInitialState() = OccupationScreenState()

    override fun handle(event: OccupationScreenEvent) {
        emitState {
            it.copy(
                currentOccupation = event.data,
                prevOccupation = it.prevOccupation,
                save = it.save.copy(enabled = true)
            )
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(OccupationScreenEffect.OnBack)
    }

    override fun handle(event: ButtonEvent) {
        if (event.id == "save") {
            onBackground {
                emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
                saveOccupationUseCase(currentState.currentOccupation!!, currentState.prevOccupation).onSuccess {
                    postEffect(OccupationScreenEffect.OnNext)
                }.onFailure {
                    emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
                }
            }
        } else if (event.id == "skip") {
            postEffect(OccupationScreenEffect.OnSkip)
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}