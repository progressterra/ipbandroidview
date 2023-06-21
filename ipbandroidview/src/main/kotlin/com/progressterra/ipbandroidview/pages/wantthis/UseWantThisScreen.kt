package com.progressterra.ipbandroidview.pages.wantthis

import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoEvent
import com.progressterra.ipbandroidview.features.documentphoto.UseDocumentPhoto
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButtonEvent
import com.progressterra.ipbandroidview.features.profilebutton.UseProfileButton
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseWantThisScreen : UseStateBox, UseTopBar, UseProfileButton, UseTextField, UseButton,
    UseDocumentPhoto {

    class Empty : UseWantThisScreen {

        override fun handle(event: DocumentPhotoEvent) = Unit

        override fun handle(event: ProfileButtonEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: StateBoxEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit
    }
}