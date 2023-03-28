package com.progressterra.ipbandroidview.ui.documents

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.entities.Document

@Immutable
data class DocumentsState(
    val documents: List<Document> = emptyList(),
    val archivedDocuments: List<Document> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING,
    val archiveButton: ButtonState = ButtonState()
)
