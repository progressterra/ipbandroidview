package com.progressterra.ipbandroidview.pages.wantthis

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButtonEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class WantThisScreenViewModel : ContainerHost<WantThisScreenState, WantThisScreenEvent>,
    ViewModel(), UseWantThisScreen {

    override val container =
        container<WantThisScreenState, WantThisScreenEvent>(WantThisScreenState())

    override fun handle(event: ProfileButtonEvent) {
        TODO("Not yet implemented")
    }

    override fun handle(event: TopBarEvent) {
        TODO("Not yet implemented")
    }

    override fun handle(event: ButtonEvent) {
        TODO("Not yet implemented")
    }

    override fun handle(event: StateBoxEvent) {
        TODO("Not yet implemented")
    }

    override fun handle(event: TextFieldEvent) {
        TODO("Not yet implemented")
    }
}