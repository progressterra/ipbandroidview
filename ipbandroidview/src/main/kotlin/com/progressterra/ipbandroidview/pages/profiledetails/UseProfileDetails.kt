package com.progressterra.ipbandroidview.pages.profiledetails

import com.progressterra.ipbandroidview.features.authprofile.UseAuthProfile
import com.progressterra.ipbandroidview.features.editbutton.UseEditButton
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox
import com.progressterra.ipbandroidview.widgets.edituser.UseEditUser

interface UseProfileDetails : UseStateBox, UseAuthProfile, UseTopBar, UseEditUser, UseEditButton