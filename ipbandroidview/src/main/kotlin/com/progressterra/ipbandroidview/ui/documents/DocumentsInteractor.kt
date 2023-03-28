package com.progressterra.ipbandroidview.ui.documents

import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.model.Document

interface DocumentsInteractor : UseButton {

    fun refresh()

    fun openDocument(document: Document)


    class Empty : DocumentsInteractor {

        override fun handle(event: ButtonEvent) = Unit

        override fun refresh() = Unit

        override fun openDocument(document: Document) = Unit
    }
}