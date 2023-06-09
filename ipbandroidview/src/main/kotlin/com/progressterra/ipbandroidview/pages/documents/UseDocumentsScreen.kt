package com.progressterra.ipbandroidview.pages.documents

import com.progressterra.ipbandroidview.features.currentcitizenship.CurrentCitizenshipEvent
import com.progressterra.ipbandroidview.features.currentcitizenship.UseCurrentCitizenship
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox
import com.progressterra.ipbandroidview.widgets.documents.DocumentsEvent
import com.progressterra.ipbandroidview.widgets.documents.UseDocuments

interface UseDocumentsScreen : UseCurrentCitizenship, UseDocuments, UseStateBox, UseTopBar {

    class Empty : UseDocumentsScreen {

        override fun handle(event: CurrentCitizenshipEvent) = Unit

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: StateBoxEvent) = Unit

        override fun handle(event: DocumentsEvent) = Unit
    }
}