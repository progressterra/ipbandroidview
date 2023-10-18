package com.progressterra.ipbandroidview.pages.datingprofile

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.DatingUser

@Immutable
data class DatingProfileScreenState(
    val user: DatingUser = DatingUser(),
    val ownProfile: Boolean = false
)