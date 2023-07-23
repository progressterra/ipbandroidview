package com.progressterra.ipbandroidview.pages.wantthis

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoState
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButtonState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.updateById
import com.progressterra.processors.IpbSubState

@Immutable
data class WantThisScreenState(
    @IpbSubState val send: ButtonState = ButtonState(id = "send"),
    @IpbSubState val cancel: ButtonState = ButtonState(id = "cancel"),
    val requests: ProfileButtonState = ProfileButtonState(id = "requests"),
    override val id: String = "",
    val screen: ScreenState = ScreenState.LOADING,
    val entries: List<TextFieldState> = emptyList(),
    val photo: DocumentPhotoState? = null
) : Id {

    fun toDocument() = Document(
        id = id,
        entries = entries,
        photo = photo
    )

    fun addPhoto(newPhoto: MultisizedImage) = copy(photo = photo?.add(newPhoto))

    fun updateById(id: Id, reducer: (TextFieldState) -> TextFieldState) = copy(
        entries = entries.updateById(id, reducer)
    )

    fun uScreenState(newScreen: ScreenState) = copy(screen = newScreen)
}