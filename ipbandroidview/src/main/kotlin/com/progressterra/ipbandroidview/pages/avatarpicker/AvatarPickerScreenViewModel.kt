package com.progressterra.ipbandroidview.pages.avatarpicker

import com.progressterra.ipbandroidview.features.avatarpicker.AvatarPickerEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class AvatarPickerScreenViewModel :
    AbstractNonInputViewModel<AvatarPickerScreenState, AvatarPickerScreenEffect>(),
    UseAvatarPickerScreen {
    override fun createInitialState() = AvatarPickerScreenState()

    override fun handle(event: AvatarPickerEvent) {

    }

    override fun handle(event: TopBarEvent) {
        postEffect(AvatarPickerScreenEffect.OnBack)
    }

    override fun handle(event: ButtonEvent) {
        postEffect(AvatarPickerScreenEffect.OnNext)
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}

