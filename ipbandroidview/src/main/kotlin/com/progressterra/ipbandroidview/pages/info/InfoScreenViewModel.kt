package com.progressterra.ipbandroidview.pages.info

import com.progressterra.ipbandroidview.features.info.InfoState
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.dating.FetchDatingUserUseCase
import com.progressterra.ipbandroidview.processes.user.SaveDatingInfoUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import kotlinx.coroutines.flow.collectLatest

class InfoScreenViewModel(
    private val saveDatingInfoUseCase: SaveDatingInfoUseCase,
    private val fetchDatingUserUseCase: FetchDatingUserUseCase
) : AbstractNonInputViewModel<InfoScreenState, InfoScreenEffect>(),
    UseInfoScreen {

    override fun createInitialState() = InfoScreenState()

    init {
        onBackground {
            fetchDatingUserUseCase.resultFlow.collectLatest { result ->
                result.onSuccess { user ->
                    emitState {
                        it.copy(
                            screen = it.screen.copy(state = ScreenState.SUCCESS), info = InfoState(
                                about = it.info.about.copy(text = user.description),
                            )
                        )
                    }
                    valid()
                }.onFailure {
                    emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
                }

            }
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(InfoScreenEffect.OnBack)
    }

    override fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            fetchDatingUserUseCase()
        }
    }

    override fun handle(event: ButtonEvent) {
        onBackground {
            if (event.id == "save") {
                saveDatingInfoUseCase(
                    currentState.info.about.formatByType()
                ).onSuccess {
                    postEffect(InfoScreenEffect.OnNext)
                }
            } else if (event.id == "skip") {
                postEffect(InfoScreenEffect.OnSkip)
            }
        }
    }

    override fun handle(event: TextFieldEvent) {
        if (event is TextFieldEvent.TextChanged) {
            if (event.id == "about") {
                emitState { it.copy(info = it.info.copy(about = it.info.about.copy(text = event.text))) }
            }
        }
        if (event is TextFieldEvent.AdditionalAction) {
            if (event.id == "about") {
                emitState { it.copy(info = it.info.copy(about = it.info.about.copy(text = ""))) }
            }
        }
        valid()
    }


    private fun valid() {
        onBackground {
            val valid = currentState.info.about.valid()
            emitState { it.copy(save = it.save.copy(enabled = valid)) }
        }
    }
}

