package com.progressterra.ipbandroidview.pages.interests

import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.dating.FetchDatingUserUseCase
import com.progressterra.ipbandroidview.processes.interests.ChangeInterestsUseCase
import com.progressterra.ipbandroidview.processes.interests.FetchInterestsUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class InterestsScreenViewModel(
    private val fetchDatingUserUseCase: FetchDatingUserUseCase,
    private val changeInterestsUseCase: ChangeInterestsUseCase,
    private val fetchInterestsUseCase: FetchInterestsUseCase
) : AbstractNonInputViewModel<InterestsScreenState, InterestsScreenEffect>(), UseInterestsScreen {

    override fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            var isSuccess = true

            fetchInterestsUseCase().onSuccess { newInterests ->
                emitState { it.copy(allInterests = newInterests) }
            }.onFailure { isSuccess = false }
            fetchDatingUserUseCase().onSuccess { user ->
                emitState {
                    it.copy(allInterests = it.allInterests.map { int ->
                        if (user.interests.contains(int)) int.copy(picked = true) else int
                    })
                }
            }.onFailure { isSuccess = false }
            emitState { it.copy(screen = it.screen.copy(state = isSuccess.toScreenState())) }
        }
    }

    override fun createInitialState() = InterestsScreenState()

    override fun handle(event: InterestsScreenEvent) {
        emitState {
            it.copy(
                changedInterests = if (it.changedInterests.contains(event.data)) it.changedInterests - event.data else it.changedInterests + event.data
            )
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(InterestsScreenEffect.OnBack)
    }

    override fun handle(event: ButtonEvent) {
        if (event.id == "save") {
            onBackground {
                emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
                changeInterestsUseCase(currentState.changedInterests).onSuccess {
                    postEffect(InterestsScreenEffect.OnNext)
                }.onFailure {
                    emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
                }
            }
        } else if (event.id == "skip") {
            postEffect(InterestsScreenEffect.OnSkip)
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}