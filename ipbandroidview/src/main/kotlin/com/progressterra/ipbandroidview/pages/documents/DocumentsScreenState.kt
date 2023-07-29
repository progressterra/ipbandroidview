package com.progressterra.ipbandroidview.pages.documents

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.currentcitizenship.CurrentCitizenshipState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.widgets.documents.DocumentsState

@Immutable
data class DocumentsScreenState(
    val documents: DocumentsState = DocumentsState(),
    val citizenship: CurrentCitizenshipState = CurrentCitizenshipState(),
    val screen: ScreenState = ScreenState.LOADING
)