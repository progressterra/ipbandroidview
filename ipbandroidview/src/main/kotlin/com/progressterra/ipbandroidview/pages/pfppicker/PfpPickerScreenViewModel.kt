package com.progressterra.ipbandroidview.pages.pfppicker

import com.progressterra.ipbandroidview.features.pfppicker.PfpPickerEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class PfpPickerScreenViewModel :
    AbstractNonInputViewModel<PfpPickerScreenState, PfpPickerScreenEffect>(), UsePfpPickerScreen {

    override fun refresh() {

    }

    override fun createInitialState() = PfpPickerScreenState()

    override fun handle(event: PfpPickerEvent) {

    }

    override fun handle(event: TopBarEvent) {
        postEffect(PfpPickerScreenEffect.Back)
    }

    override fun handle(event: ButtonEvent) {
        if (event.id == "choose") postEffect(PfpPickerScreenEffect.Next)
        if (event.id == "skip") postEffect(PfpPickerScreenEffect.Skip)
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}

