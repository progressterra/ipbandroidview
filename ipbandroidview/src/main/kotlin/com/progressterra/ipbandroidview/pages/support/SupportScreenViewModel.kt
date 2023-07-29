package com.progressterra.ipbandroidview.pages.support

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class SupportScreenViewModel : BaseViewModel<SupportScreenState, SupportScreenEvent>(),
    UseSupportScreen {

    override val initialState = SupportScreenState()

    override fun handle(event: TopBarEvent) {
        TODO("Not yet implemented")
    }

    override fun handle(event: StateBoxEvent) {
        TODO("Not yet implemented")
    }

    override fun handle(event: TextFieldEvent) {
        TODO("Not yet implemented")
    }
}