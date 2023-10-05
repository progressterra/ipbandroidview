package com.progressterra.ipbandroidview.pages.info

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.SaveDatingInfoUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
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
        if (event.id == "save") {
            postEffect(InfoScreenEffect.OnNext)
        } else if (event.id == "skip") {
            postEffect(InfoScreenEffect.OnSkip)
        }
    }

    private fun saveAndProceed() {

    }

    override fun handle(event: StateColumnEvent) {

    }

    override fun handle(event: TextFieldEvent) {

    }
}

