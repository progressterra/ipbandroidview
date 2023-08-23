package com.progressterra.ipbandroidview.pages.profiledetails

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.authprofile.AuthProfileState
import com.progressterra.ipbandroidview.features.editbutton.EditButtonState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumnState
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState

@Immutable
data class ProfileDetailsState(
    val editUser: EditUserState = EditUserState(),
    val editButton: EditButtonState = EditButtonState(),
    val authProfileState: AuthProfileState = AuthProfileState(),
    val screen: StateColumnState = StateColumnState()
) {

    companion object
}
