package com.progressterra.ipbandroidview.entities

import android.os.Parcelable
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidview.features.bankcard.BankCardState
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoState
import com.progressterra.ipbandroidview.features.wantthiscard.WantThisCardState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import kotlinx.parcelize.Parcelize


@Parcelize
data class Document(
    override val id: String = "",
    val status: TypeStatusDoc = TypeStatusDoc.NOT_FILL,
    val name: String = "",
    val entries: List<TextFieldState> = emptyList(),
    val photo: DocumentPhotoState = DocumentPhotoState(),
    val additionalValue: String = ""
) : Id, Parcelable, IsEmpty {

    fun isTemplate(): Boolean = id == ""

    override fun isEmpty(): Boolean = this == Document()

    fun toWantThisCardState() = WantThisCardState(
        id = id,
        image = photo.items.firstOrNull()?.url ?: "",
        status = status,
        name = entries.firstOrNull { it.label == "Наименование" }?.text ?: "",
        document = this
    )


    fun fromTemplateToReal(real: Document) = real.copy(
        entries = entries,
        photo = photo
    )

    fun toBankCardState() = BankCardState(
        id = id,
        document = this
    )
}
