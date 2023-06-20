package com.progressterra.ipbandroidview.pages.wantthis

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButtonState
import com.progressterra.ipbandroidview.pages.documentdetails.DocumentDetailsState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.processors.IpbSubState

@Immutable
data class WantThisScreenState(
    @IpbSubState val name: TextFieldState = TextFieldState(id = "name"),
    @IpbSubState val link: TextFieldState = TextFieldState(id = "link"),
    @IpbSubState val send: ButtonState = ButtonState(id = "send"),
    @IpbSubState val cancel: ButtonState = ButtonState(id = "cancel"),
    val requests: ProfileButtonState = ProfileButtonState(id = "requests"),
    val images: DocumentDetailsState = DocumentDetailsState(),
    val screen: ScreenState = ScreenState.LOADING,
    val document: Document = Document()
)