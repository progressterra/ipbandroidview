package com.progressterra.ipbandroidview.pages.info

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.SaveDatingInfoUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class InfoScreenViewModel(
    private val saveDatingInfoUseCase: SaveDatingInfoUseCase
) : AbstractNonInputViewModel<InfoScreenState, InfoScreenEffect>(),
    UseInfoScreen {

    override fun createInitialState() = InfoScreenState()
    override fun handle(event: TopBarEvent) {
        postEffect(InfoScreenEffect.OnBack)
    }

    override fun handle(event: ButtonEvent) {
        onBackground {
            if (event.id == "save") {
                saveDatingInfoUseCase(
                    currentState.info.nickName.text,
                    currentState.info.about.text
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

