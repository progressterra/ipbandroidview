package com.progressterra.ipbandroidview.ui.documents

import com.progressterra.ipbandroidview.model.checklist.Document

interface DocumentsInteractor {

    fun refresh()

    fun openArchive()

    fun openDocument(document: Document)

    class Empty : DocumentsInteractor {

        override fun refresh() = Unit

        override fun openArchive() = Unit

        override fun openDocument(document: Document) = Unit
    }
}