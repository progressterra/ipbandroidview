package com.progressterra.ipbandroidview.pages.targetpicker

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.dating.AvailableTargetsUseCase
import com.progressterra.ipbandroidview.processes.dating.FetchDatingUserUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import kotlinx.coroutines.flow.collectLatest

class TargetPickerScreenViewModel(
    private val fetchDatingUserUseCase: FetchDatingUserUseCase,
    private val availableTargetsUseCase: AvailableTargetsUseCase

) :
    AbstractNonInputViewModel<TargetPickerScreenState, TargetPickerScreenEffect>(),
    UseTargetPickerScreen {

    override fun createInitialState() = TargetPickerScreenState()

    init {
        onBackground {
            fetchDatingUserUseCase.resultFlow.collectLatest { result ->
                result.onSuccess { user ->
                    emitState {
                        it.copy(
                            selectedTarget = user.target,
                            save = it.save.copy(enabled = !user.target.isEmpty())
                        )
                    }
                }
            }
        }
    }

    override fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            availableTargetsUseCase().onSuccess { targets ->
                emitState {
                    it.copy(
                        screen = it.screen.copy(state = ScreenState.SUCCESS),
                        targets = targets
                    )
                }
            }.onFailure {
                emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
            }
            fetchDatingUserUseCase()
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(TargetPickerScreenEffect.OnBack)
    }

    override fun handle(event: TargetPickerScreenEvent) {
        emitState { it.copy(selectedTarget = event.data, save = it.save.copy(enabled = true)) }
    }

    override fun handle(event: ButtonEvent) {
        if (event.id == "save") {
            postEffect(TargetPickerScreenEffect.OnNext(currentState.selectedTarget))
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}