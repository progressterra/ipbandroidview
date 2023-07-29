package com.progressterra.ipbandroidview.widgets.documents

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Document

@Immutable
data class DocumentsState(
    val items: List<Document> = emptyList()
)