package com.progressterra.ipbandroidview.pages.info

import com.progressterra.ipbandroidview.features.info.InfoState
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.dating.FetchDatingUserUseCase
import com.progressterra.ipbandroidview.processes.user.SaveDatingInfoUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class InfoScreenViewModel(
    private val saveDatingInfoUseCase: SaveDatingInfoUseCase,
    private val fetchDatingUserUseCase: FetchDatingUserUseCase
) : AbstractNonInputViewModel<InfoScreenState, InfoScreenEffect>(),
    UseInfoScreen {

    override fun createInitialState() = InfoScreenState()
    override fun handle(event: TopBarEvent) {
        postEffect(InfoScreenEffect.OnBack)
    }

    override fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            fetchDatingUserUseCase().onSuccess { user ->
                emitState {
                    it.copy(
                        screen = it.screen.copy(state = ScreenState.SUCCESS), info = InfoState(
                            nickName = it.info.nickName.copy(text = user.name),
                            about = it.info.about.copy(text = user.description),
                        )
                    )
                }
            }
        }
    }

    override fun handle(event: ButtonEvent) {
        onBackground {
            if (event.id == "save") {
                saveDatingInfoUseCase(
                    currentState.info.nickName.formatByType(),
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
            if (event.id == "nickName") {
                emitState { it.copy(info = it.info.copy(nickName = it.info.nickName.copy(text = event.text))) }
            } else if (event.id == "about") {
                emitState { it.copy(info = it.info.copy(about = it.info.about.copy(text = event.text))) }
            }
        }
        if (event is TextFieldEvent.AdditionalAction) {
            if (event.id == "nickName") {
                emitState { it.copy(info = it.info.copy(nickName = it.info.nickName.copy(text = ""))) }
            } else if (event.id == "about") {
                emitState { it.copy(info = it.info.copy(about = it.info.about.copy(text = ""))) }
            }
        }
    }
}

