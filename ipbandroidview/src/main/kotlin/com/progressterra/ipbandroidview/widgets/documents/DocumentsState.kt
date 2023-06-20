package com.progressterra.ipbandroidview.widgets.documents

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

@Immutable
data class DocumentsState(
    val items: List<Document> = emptyList()
)