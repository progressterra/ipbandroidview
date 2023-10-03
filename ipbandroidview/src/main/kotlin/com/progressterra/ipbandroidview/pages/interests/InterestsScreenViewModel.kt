package com.progressterra.ipbandroidview.pages.interests

import com.progressterra.ipbandroidview.features.interestspicker.InterestsPickerEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class InterestsScreenViewModel :
    AbstractNonInputViewModel<InterestsScreenState, InterestsScreenEffect>(), UseInterestsScreen {

    override fun refresh() {

    }

    override fun createInitialState() = InterestsScreenState()

    override fun handle(event: InterestsPickerEvent) {

    }

    override fun handle(event: TopBarEvent) {
        postEffect(InterestsScreenEffect.OnBack)
    }

    override fun handle(event: ButtonEvent) {
        if (event.id == "save") {

        } else if (event.id == "skip") {

        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}