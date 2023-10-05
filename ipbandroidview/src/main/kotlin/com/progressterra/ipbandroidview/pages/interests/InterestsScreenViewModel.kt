package com.progressterra.ipbandroidview.pages.interests

import com.progressterra.ipbandroidview.features.interestspicker.InterestsPickerEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.interests.ChangeInterestsUseCase
import com.progressterra.ipbandroidview.processes.interests.FetchInterestsUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class InterestsScreenViewModel(
    private val changeInterestsUseCase: ChangeInterestsUseCase,
    private val fetchInterestsUseCase: FetchInterestsUseCase
) :
    AbstractNonInputViewModel<InterestsScreenState, InterestsScreenEffect>(), UseInterestsScreen {

    override fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            fetchInterestsUseCase().onSuccess { newInterests ->
                emitState {
                    it.copy(
                        screen = it.screen.copy(state = ScreenState.SUCCESS),
                        interests = it.interests.copy(
                            allInterests = newInterests
                        )
                    )
                }
            }.onFailure {
                emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
            }
        }
    }

    override fun createInitialState() = InterestsScreenState()

    override fun handle(event: InterestsPickerEvent) {
        emitState {
            it.copy(
                interests = it.interests.copy(
                    changedInterests = it.interests.changedInterests + event.data
                )
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
                changeInterestsUseCase(currentState.interests.changedInterests).onSuccess {
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