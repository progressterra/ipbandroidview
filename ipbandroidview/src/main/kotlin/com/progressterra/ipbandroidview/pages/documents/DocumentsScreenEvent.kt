package com.progressterra.ipbandroidview.pages.documents

import com.progressterra.ipbandroidview.pages.documentdetails.DocumentDetailsState

sealed class DocumentsScreenEvent {

    data object Back : DocumentsScreenEvent()

    class OpenDocument(val item: DocumentDetailsState) : DocumentsScreenEvent()
}