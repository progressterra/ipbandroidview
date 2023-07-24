package com.progressterra.ipbandroidview.pages.documentdetails

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.updateById
import com.progressterra.processors.IpbSubState
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class DocumentDetailsState(
    override val id: String = "",
    val docName: String = "",
    val entries: List<TextFieldState> = emptyList(),
    val photo: DocumentPhotoState = DocumentPhotoState(),
    @IpbSubState val apply: ButtonState = ButtonState(
        id = "apply"
    ),
    val canBeEdit: Boolean = true
) : Id, Parcelable {

    fun uId(newId: String) = copy(id = newId)

    fun addPhoto(newPhoto: MultisizedImage) = copy(photo = photo.add(newPhoto))

    fun updateById(id: Id, reducer: (TextFieldState) -> TextFieldState) = copy(
        entries = entries.updateById(id, reducer)
    )

    fun toDocument() = Document(
        id = id,
        docName = docName,
        entries = entries,
        photo = photo
    )
}