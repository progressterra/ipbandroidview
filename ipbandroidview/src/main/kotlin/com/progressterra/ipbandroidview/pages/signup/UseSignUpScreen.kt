package com.progressterra.ipbandroidview.pages.signup

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.widgets.edituser.EditUserEvent
import com.progressterra.ipbandroidview.widgets.edituser.UseEditUser

interface UseSignUpScreen : UseTopBar, UseEditUser, UseButton {

    class Empty : UseSignUpScreen {

        override fun handle(event: EditUserEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit
    }
}