package com.progressterra.ipbandroidview.pages.documents

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.currentcitizenship.CurrentCitizenshipState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import com.progressterra.ipbandroidview.widgets.documents.DocumentsState

@Immutable
data class DocumentsScreenState(
    val documents: DocumentsState = DocumentsState(),
    val citizenship: CurrentCitizenshipState = CurrentCitizenshipState(),
    val screen: StateBoxState = StateBoxState()
)