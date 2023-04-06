package com.progressterra.ipbandroidview.pages.welcome

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.authorskip.AuthOrSkipState

@Immutable
data class WelcomeState(
    val authOrSkipState: AuthOrSkipState = AuthOrSkipState()
)