package com.progressterra.ipbandroidview.pages.documentdetails

import com.progressterra.ipbandroidview.features.attachablechat.AttachableChatEvent
import com.progressterra.ipbandroidview.features.attachablechat.UseAttachableChat
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoEvent
import com.progressterra.ipbandroidview.features.documentphoto.UseDocumentPhoto
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseDocumentDetails : UseTopBar, UseTextField, UseDocumentPhoto, UseButton,
    UseAttachableChat {

    class Empty : UseDocumentDetails {

        override fun handle(event: StateBoxEvent) = Unit

        override fun handle(event: AttachableChatEvent) = Unit

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit

        override fun handle(event: DocumentPhotoEvent) = Unit
    }
}