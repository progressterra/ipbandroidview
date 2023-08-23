package com.progressterra.ipbandroidview.pages.wantthis

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButtonState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumnState

@Immutable
data class WantThisScreenState(
    val send: ButtonState = ButtonState(id = "send"),
    val requests: ProfileButtonState = ProfileButtonState(id = "requests"),
    val screen: StateColumnState = StateColumnState(),
    val document: Document = Document()
)