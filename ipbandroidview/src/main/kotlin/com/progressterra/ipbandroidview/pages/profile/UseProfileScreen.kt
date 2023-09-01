package com.progressterra.ipbandroidview.pages.profile

import com.progressterra.ipbandroidview.features.authprofile.UseAuthProfile
import com.progressterra.ipbandroidview.features.profilebutton.UseProfileButton
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn

interface UseProfileScreen : UseStateColumn, UseProfileButton, UseAuthProfile, UseButton, UseTopBar