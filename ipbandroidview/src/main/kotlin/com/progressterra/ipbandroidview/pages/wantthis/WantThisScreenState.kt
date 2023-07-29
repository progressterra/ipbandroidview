package com.progressterra.ipbandroidview.pages.wantthis

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoState
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButtonState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

@Immutable
data class WantThisScreenState(
    override val id: String = "",
    val send: ButtonState = ButtonState(id = "send"),
    val cancel: ButtonState = ButtonState(id = "cancel"),
    val requests: ProfileButtonState = ProfileButtonState(id = "requests"),
    val screen: ScreenState = ScreenState.LOADING,
    val entries: List<TextFieldState> = emptyList(),
    val photo: DocumentPhotoState = DocumentPhotoState()
) : Id {

    fun toDocument() = Document(
        id = id,
        entries = entries,
        photo = photo
    )

    companion object
}