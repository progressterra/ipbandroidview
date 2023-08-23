package com.progressterra.ipbandroidview.pages.documents

import com.progressterra.ipbandroidview.entities.Document

sealed class DocumentsScreenEvent {

    data object Back : DocumentsScreenEvent()

    class OpenDocument(val item: Document) : DocumentsScreenEvent()
}