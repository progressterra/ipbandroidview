package com.progressterra.ipbandroidview.pages.proshkawelcome

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.authorskip.AuthOrSkipState

@Immutable
data class ProshkaWelcomeState(
    val authOrSkipState: AuthOrSkipState = AuthOrSkipState()
)