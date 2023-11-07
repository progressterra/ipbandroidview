package com.progressterra.ipbandroidview.pages.profiledetails

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.editbutton.EditButtonState
import com.progressterra.ipbandroidview.features.editprofile.EditProfileState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState

@Immutable
data class ProfileDetailsState(
    val editProfile: EditProfileState = EditProfileState(),
    val editUser: EditUserState = EditUserState(),
    val editButton: EditButtonState = EditButtonState(),
    val screen: StateColumnState = StateColumnState()
) {

    companion object
}
