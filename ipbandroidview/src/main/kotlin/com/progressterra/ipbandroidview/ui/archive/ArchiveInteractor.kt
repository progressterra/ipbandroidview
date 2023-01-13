package com.progressterra.ipbandroidview.ui.archive

import com.progressterra.ipbandroidview.model.checklist.Document

interface ArchiveInteractor {

    fun onBack()

    fun openDocument(document: Document)

    class Empty : ArchiveInteractor {

        override fun onBack() = Unit

        override fun openDocument(document: Document) = Unit
    }
}