package com.progressterra.ipbandroidview.widgets.documents

import com.progressterra.ipbandroidview.entities.Id

sealed class DocumentsEvent(state: DocumentsState.Item) : Id by state {

    class Click(state: DocumentsState.Item) : DocumentsEvent(state)
}