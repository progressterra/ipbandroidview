package com.progressterra.ipbandroidview.pages.datingprofile

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.AnotherUser

@Immutable
data class DatingProfileScreenState(
    val user: AnotherUser = AnotherUser()
)