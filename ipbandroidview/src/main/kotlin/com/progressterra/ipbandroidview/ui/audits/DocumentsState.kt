package com.progressterra.ipbandroidview.ui.audits

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.core.ScreenState

@Immutable
data class DocumentsState(
    val documents: List<Document> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING
)
