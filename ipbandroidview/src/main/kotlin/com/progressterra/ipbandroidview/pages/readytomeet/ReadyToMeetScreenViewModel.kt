package com.progressterra.ipbandroidview.pages.readytomeet

import com.progressterra.ipbandroidview.entities.DatingTarget
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.dating.DeleteReadyToMeetUseCase
import com.progressterra.ipbandroidview.processes.dating.FetchDatingUserUseCase
import com.progressterra.ipbandroidview.processes.dating.ReadyToMeetUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import kotlinx.coroutines.flow.collectLatest

class ReadyToMeetScreenViewModel(
    private val fetchDatingUserUseCase: FetchDatingUserUseCase,
    private val readyToMeetUseCase: ReadyToMeetUseCase,
    private val deleteReadyToMeetUseCase: DeleteReadyToMeetUseCase
) : UseReadyToMeetScreen,
    AbstractInputViewModel<DatingTarget, ReadyToMeetScreenState, ReadyToMeetScreenEffect>() {

    init {
        onBackground {
            fetchDatingUserUseCase.resultFlow.collectLatest { result ->
                result.onSuccess { user ->
                    emitState {
                        it.copy(
                            user = user,
                            save = it.save.copy(enabled = !user.target.isEmpty()),
                            screen = it.screen.copy(state = ScreenState.SUCCESS)
                        )
                    }
                }.onFailure {
                    emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
                }
            }
        }
    }

    override fun createInitialState() = ReadyToMeetScreenState()

    override fun setup(data: DatingTarget) {
        emitState { it.copy(target = data) }
        refresh()
    }

    fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            fetchDatingUserUseCase()
        }
    }

    override fun handle(event: ReadyToMeetScreenEvent) {
        emitState { it.copy(readyToMeet = event.data) }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(ReadyToMeetScreenEffect.OnBack)
    }

    override fun handle(event: ButtonEvent) {
        onBackground {
            if (event.id == "save") {
                if (currentState.readyToMeet != null || !currentState.target.isEmpty()) {
                    readyToMeetUseCase(currentState.target).onSuccess {
                        postEffect(ReadyToMeetScreenEffect.OnNext)
                    }
                } else {
                    deleteReadyToMeetUseCase().onSuccess {
                        postEffect(ReadyToMeetScreenEffect.OnNext)
                    }
                }
            } else if (event.id == "skip") {
                postEffect(ReadyToMeetScreenEffect.OnNext)
            }
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}