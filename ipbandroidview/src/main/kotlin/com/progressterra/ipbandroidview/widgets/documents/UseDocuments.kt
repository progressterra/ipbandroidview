package com.progressterra.ipbandroidview.widgets.documents

interface UseDocuments {

    fun handle(event: DocumentsEvent)

    class Empty : UseDocuments {

        override fun handle(event: DocumentsEvent) = Unit
    }
}