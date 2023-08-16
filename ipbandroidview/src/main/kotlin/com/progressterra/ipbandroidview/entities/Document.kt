package com.progressterra.ipbandroidview.entities

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidview.features.bankcard.BankCardState
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoState
import com.progressterra.ipbandroidview.features.wantthiscard.WantThisCardState
import com.progressterra.ipbandroidview.pages.documentdetails.DocumentDetailsState
import com.progressterra.ipbandroidview.pages.wantthis.WantThisScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

@Immutable
data class Document(
    override val id: String = "",
    val status: TypeStatusDoc = TypeStatusDoc.NOT_FILL,
    val name: String = "",
    val entries: List<TextFieldState> = emptyList(),
    val photo: DocumentPhotoState = DocumentPhotoState(),
    val additionalValue: String = ""
) : Id {

    fun toWantThisCardState() = WantThisCardState(
        id = id,
        image = photo.items.firstOrNull()?.url ?: "",
        status = status,
        name = entries.firstOrNull { it.label == "Наименование" }?.text ?: ""
    )

    fun toWantThisScreenState() = WantThisScreenState(
        id = id,
        entries = entries,
        photo = photo
    )

    fun toDocumentDetailsState(): DocumentDetailsState {
        val canBeEdit = status.toCanBeEdit()
        return DocumentDetailsState(
            id = id,
            name = name,
            canBeEdit = canBeEdit,
            entries = entries.map { it.copy(enabled = canBeEdit) },
            photo = photo.copy(enabled = canBeEdit),
            apply = ButtonState(
                id = "apply", enabled = false
            )
        )
    }

    fun fromTemplateToReal(real: Document) = real.copy(
        entries = entries,
        photo = photo
    )

    fun toBankCardState() = BankCardState(
        id = id,
        name = name,
        entries = entries,
        photo = photo,
        status = status
    )
}
