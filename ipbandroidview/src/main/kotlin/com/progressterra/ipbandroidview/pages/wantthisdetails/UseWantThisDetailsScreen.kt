package com.progressterra.ipbandroidview.pages.wantthisdetails

import com.progressterra.ipbandroidview.features.attachablechat.AttachableChatEvent
import com.progressterra.ipbandroidview.features.attachablechat.UseAttachableChat
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoEvent
import com.progressterra.ipbandroidview.features.documentphoto.UseDocumentPhoto
import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.features.storecard.UseStoreCard
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseWantThisDetailsScreen : UseTopBar,
    UseTextField,
    UseAttachableChat,
    UseDocumentPhoto,
    UseStoreCard,
    UseButton {

    fun handle(event: WantThisDetailsScreenEvent)

    class Empty : UseWantThisDetailsScreen {

        override fun handle(event: AttachableChatEvent) = Unit

        override fun handle(event: DocumentPhotoEvent) = Unit

        override fun handle(event: StoreCardEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: WantThisDetailsScreenEvent) = Unit

        override fun handle(event: ButtonEvent) = Unit

        override fun handle(event: CounterEvent) = Unit

        override fun handle(event: StateColumnEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit
    }
}