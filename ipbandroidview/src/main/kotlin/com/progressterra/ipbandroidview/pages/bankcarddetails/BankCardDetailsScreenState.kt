package com.progressterra.ipbandroidview.pages.bankcarddetails

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoState
import com.progressterra.ipbandroidview.shared.IsEmpty
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class BankCardDetailsScreenState(
    override val id: String = "",
    val entries: List<TextFieldState> = emptyList(),
    val photo: DocumentPhotoState = DocumentPhotoState(),
    val apply: ButtonState = ButtonState(id = "apply"),
    val canBeEdited: Boolean = true,
    val number: String = "",
    val isMainCard: Boolean = false,
    val status: TypeStatusDoc = TypeStatusDoc.NOT_FILL,
    val isNew: Boolean = true,
    val screen: ScreenState = ScreenState.LOADING
) : Id, IsEmpty, Parcelable {

    override fun isEmpty(): Boolean = this == BankCardDetailsScreenState()

    fun toDocument() = Document(
        id = id,
        status = status,
        name = number,
        entries = entries,
        photo = photo
    )
}