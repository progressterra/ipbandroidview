package com.progressterra.ipbandroidview.features.search

import androidx.compose.runtime.Immutable
import com.progressterra.processors.IpbState

@Immutable
@IpbState
data class SearchState(
    val text: String = ""
)