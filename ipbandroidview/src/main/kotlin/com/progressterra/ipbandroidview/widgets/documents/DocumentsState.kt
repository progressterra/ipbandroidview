package com.progressterra.ipbandroidview.widgets.documents

import androidx.compose.runtime.Immutable
import arrow.optics.optics
import com.progressterra.ipbandroidview.entities.Document

@Immutable
@optics data class DocumentsState(
    val items: List<Document> = emptyList()
) { companion object }