package com.progressterra.ipbandroidview.pages.documents

import com.progressterra.ipbandroidview.entities.Document

sealed class DocumentsScreenEffect {

    data object Back : DocumentsScreenEffect()

    class OpenDocument(val data: Document) : DocumentsScreenEffect()
}