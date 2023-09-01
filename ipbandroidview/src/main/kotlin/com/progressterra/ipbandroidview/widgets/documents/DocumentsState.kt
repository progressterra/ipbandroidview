package com.progressterra.ipbandroidview.widgets.documents

import com.progressterra.ipbandroidview.entities.Document


data class DocumentsState(
    val items: List<Document> = emptyList()
)