package com.progressterra.ipbandroidview.entities

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.updateById

@Immutable
data class Document(
    override val id: String = "",
    val status: TypeStatusDoc = TypeStatusDoc.NOT_FILL,
    val docName: String = "",
    val entries: List<TextFieldState> = emptyList(),
    val photo: DocumentPhotoState? = null,
    val additionalValue: String = ""
) : Id {

    fun updateById(id: Id, reducer: (TextFieldState) -> TextFieldState) = copy(
        entries = entries.updateById(id, reducer)
    )
}
