package com.progressterra.ipbandroidview.pages.occupacion

import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.dating.FetchDatingUserUseCase
import com.progressterra.ipbandroidview.processes.occupation.FetchOccupationsUseCase
import com.progressterra.ipbandroidview.processes.occupation.SaveOccupationUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import kotlinx.coroutines.flow.collectLatest

class OccupationScreenViewModel(
    private val saveOccupationUseCase: SaveOccupationUseCase,
    private val fetchOccupationsUseCase: FetchOccupationsUseCase,
    private val fetchDatingUserUseCase: FetchDatingUserUseCase
) :
    AbstractNonInputViewModel<OccupationScreenState, OccupationScreenEffect>(),
    UseOccupationScreen {

    init {
        onBackground {
            fetchDatingUserUseCase.resultFlow.collectLatest { result ->
                result.onSuccess { user ->
                    emitState {
                        it.copy(
                            userOccupation = user.occupation,
                            save = it.save.copy(enabled = !user.occupation.isEmpty())
                        )
                    }
                }.onFailure {
                    emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
                }
            }
        }
    }

    override fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            var isSuccess = true
            fetchOccupationsUseCase().onSuccess { newOccupations ->
                emitState {
                    it.copy(allOccupations = newOccupations)
                }
            }.onFailure { isSuccess = false }
            fetchDatingUserUseCase()
            emitState { it.copy(screen = it.screen.copy(state = isSuccess.toScreenState())) }
        }
    }

    override fun createInitialState() = OccupationScreenState()

    override fun handle(event: OccupationScreenEvent) {
        emitState {
            it.copy(
                pickedOccupation = event.data,
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
                saveOccupationUseCase(
                    currentState.pickedOccupation,
                    currentState.userOccupation
                ).onSuccess {
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