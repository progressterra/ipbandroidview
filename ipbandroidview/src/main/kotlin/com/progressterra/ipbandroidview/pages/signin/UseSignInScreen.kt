package com.progressterra.ipbandroidview.pages.signin

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.linktext.LinkTextEvent
import com.progressterra.ipbandroidview.shared.ui.linktext.UseLinkText
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseSignInScreen : UseButton, UseTextField, UseTopBar, UseLinkText {

    class Empty : UseSignInScreen {

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: LinkTextEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit

    }
}