package com.progressterra.ipbandroidview.ui.documents

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.composable.component.ButtonState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.Document

@Immutable
data class DocumentsState(
    val documents: List<Document> = emptyList(),
    val archivedDocuments: List<Document> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING,
    val archiveButton: ButtonState = ButtonState()
)
