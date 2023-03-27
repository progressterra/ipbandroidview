package com.progressterra.ipbandroidview.ui.profiledetails

import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.UseTextField

interface ProfileDetailsInteractor : UseTextField, UseButton {

    fun onBack()

    class Empty : ProfileDetailsInteractor {

        override fun handle(id: String, event: ButtonEvent) = Unit

        override fun handle(id: String, event: TextFieldEvent) = Unit

        override fun onBack() = Unit
    }
}