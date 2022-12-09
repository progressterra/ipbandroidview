package com.progressterra.ipbandroidview.ui.documents

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.checklist.Document

@Immutable
data class DocumentsState(
    val documents: List<Document> = emptyList(),
    val archivedDocuments: List<Document> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING
)
