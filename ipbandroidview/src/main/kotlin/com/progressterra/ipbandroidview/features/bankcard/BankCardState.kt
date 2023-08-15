package com.progressterra.ipbandroidview.features.bankcard

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.entities.toCanBeEdit
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoState
import com.progressterra.ipbandroidview.pages.bankcarddetails.BankCardDetailsScreenState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

@Immutable
data class BankCardState(
    override val id: String = "",
    val isMainCard: Boolean = false,
    val number: String = "",
    val entries: List<TextFieldState> = emptyList(),
    val photo: DocumentPhotoState = DocumentPhotoState(),
    val status: TypeStatusDoc = TypeStatusDoc.NOT_FILL
) : Id {

    fun toBankCardDetailsScreenState(): BankCardDetailsScreenState {
        val canBeEdit = status.toCanBeEdit()
        return BankCardDetailsScreenState(
            id = id,
            entries = entries.map { it.copy(enabled = canBeEdit) },
            photo = photo.copy(enabled = canBeEdit),
            canBeEdited = canBeEdit,
            isMainCard = isMainCard,
            isNew = false
        )
    }
}