package com.progressterra.ipbandroidview.pages.datingprofile

import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseDatingProfileScreen : UseButton, UseTextField {

    fun handle(event: DatingProfileScreenEvent)

    class Empty : UseDatingProfileScreen {

        override fun handle(event: DatingProfileScreenEvent) = Unit

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit
    }
}