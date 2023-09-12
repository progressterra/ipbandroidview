package com.progressterra.ipbandroidview.pages.profiledetails

import com.progressterra.ipbandroidview.features.editbutton.EditButtonState
import com.progressterra.ipbandroidview.features.editprofile.EditProfileState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState

data class ProfileDetailsState(
    val editProfile: EditProfileState = EditProfileState(),
    val editUser: EditUserState = EditUserState(),
    val editButton: EditButtonState = EditButtonState(),
    val screen: StateColumnState = StateColumnState()
) {

    companion object
}
