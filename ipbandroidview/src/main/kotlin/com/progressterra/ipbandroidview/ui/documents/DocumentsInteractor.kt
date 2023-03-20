package com.progressterra.ipbandroidview.ui.documents

import com.progressterra.ipbandroidview.shared.ui.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.UseButton
import com.progressterra.ipbandroidview.model.Document

interface DocumentsInteractor : UseButton {

    fun refresh()

    fun openDocument(document: Document)


    class Empty : DocumentsInteractor {

        override fun handleEvent(id: String, event: ButtonEvent) = Unit

        override fun refresh() = Unit

        override fun openDocument(document: Document) = Unit
    }
}