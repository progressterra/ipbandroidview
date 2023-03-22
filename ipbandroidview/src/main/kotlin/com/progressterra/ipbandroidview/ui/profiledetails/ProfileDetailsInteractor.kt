package com.progressterra.ipbandroidview.ui.profiledetails

import com.progressterra.ipbandroidview.shared.ui.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.UseButton
import com.progressterra.ipbandroidview.shared.ui.UseTextField

interface ProfileDetailsInteractor : UseTextField, UseButton {

    fun onBack()

    class Empty : ProfileDetailsInteractor {

        override fun handleEvent(id: String, event: ButtonEvent) = Unit

        override fun handleEvent(id: String, event: TextFieldEvent) = Unit

        override fun onBack() = Unit
    }
}