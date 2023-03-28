package com.progressterra.ipbandroidview.ui.profiledetails

import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface ProfileDetailsInteractor : UseTextField, UseButton {

    fun onBack()

    class Empty : ProfileDetailsInteractor {

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit

        override fun onBack() = Unit
    }
}