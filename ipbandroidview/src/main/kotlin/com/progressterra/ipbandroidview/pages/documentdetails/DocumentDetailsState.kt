package com.progressterra.ipbandroidview.pages.documentdetails

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class DocumentDetailsState(
    override val id: String = "",
    val name: String = "",
    val entries: List<TextFieldState> = emptyList(),
    val photo: DocumentPhotoState = DocumentPhotoState(),
    val apply: ButtonState = ButtonState(id = "apply"),
    val canBeEdit: Boolean = true
) : Id, Parcelable {

    fun toDocument() = Document(
        id = id,
        name = name,
        entries = entries,
        photo = photo
    )

    companion object
}