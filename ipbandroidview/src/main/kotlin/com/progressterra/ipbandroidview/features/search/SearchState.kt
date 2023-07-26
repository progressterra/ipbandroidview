package com.progressterra.ipbandroidview.features.search

import androidx.compose.runtime.Immutable
import arrow.optics.optics

@Immutable
@optics data class SearchState(
    val text: String = ""
) { companion object }