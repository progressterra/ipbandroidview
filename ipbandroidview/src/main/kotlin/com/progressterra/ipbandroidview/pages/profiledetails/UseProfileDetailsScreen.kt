package com.progressterra.ipbandroidview.pages.profiledetails

import com.progressterra.ipbandroidview.features.editbutton.UseEditButton
import com.progressterra.ipbandroidview.features.editprofile.UseEditProfile
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn
import com.progressterra.ipbandroidview.widgets.edituser.UseEditUser

interface UseProfileDetailsScreen : UseStateColumn, UseTopBar, UseEditUser,
    UseEditButton, UseEditProfile