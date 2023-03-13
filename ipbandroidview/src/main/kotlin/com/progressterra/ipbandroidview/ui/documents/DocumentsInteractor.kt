package com.progressterra.ipbandroidview.ui.documents

import com.progressterra.ipbandroidview.model.Document

interface DocumentsInteractor {

    fun refresh()

    fun openDocument(document: Document)

    class Empty : DocumentsInteractor {

        override fun refresh() = Unit

        override fun openDocument(document: Document) = Unit
    }
}