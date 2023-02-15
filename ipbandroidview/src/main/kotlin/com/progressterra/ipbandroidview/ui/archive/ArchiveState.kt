package com.progressterra.ipbandroidview.ui.archive

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.Document

@Immutable
data class ArchiveState(
    val documents: List<Document> = emptyList(), val screenState: ScreenState = ScreenState.LOADING
)
