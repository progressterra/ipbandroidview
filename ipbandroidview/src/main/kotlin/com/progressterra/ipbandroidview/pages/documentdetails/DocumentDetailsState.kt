package com.progressterra.ipbandroidview.pages.documentdetails

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.AdaptiveEntry
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.updateById
import com.progressterra.processors.IpbSubState

@Immutable
data class DocumentDetailsState(
    override val id: String = "",
    val docName: String = "",
    val entries: List<TextFieldState> = emptyList(),
    val photo: DocumentPhotoState? = null,
    @IpbSubState val apply: ButtonState = ButtonState(
        id = "apply"
    )
) : Id {

    fun uId(newId: String) = copy(id = newId)

    fun addPhoto(newPhoto: MultisizedImage) = copy(photo = photo?.add(newPhoto))

    fun uState(newEntries: List<TextFieldState>, newPhoto: DocumentPhotoState?) = copy(
        entries = newEntries, photo = newPhoto
    )

    fun updateById(id: Id, reducer: (TextFieldState) -> TextFieldState) = copy(
        entries = entries.updateById(id, reducer)
    )
}