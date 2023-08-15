package com.progressterra.ipbandroidview.pages.bankcarddetails

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoState
import com.progressterra.ipbandroidview.shared.IsEmpty
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

@Immutable
data class BankCardDetailsScreenState(
    override val id: String = "",
    val entries: List<TextFieldState> = emptyList(),
    val photo: DocumentPhotoState = DocumentPhotoState(),
    val apply: ButtonState = ButtonState(id = "apply"),
    val canBeEdited: Boolean = true,
    val isMainCard: Boolean = false,
    val isNew: Boolean = false,
    val screen: ScreenState = ScreenState.LOADING
) : Id, IsEmpty {

    override fun isEmpty(): Boolean = this == BankCardDetailsScreenState()
}