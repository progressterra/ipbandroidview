package com.progressterra.ipbandroidview.ui.auditchecks

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.core.ScreenState

@Immutable
data class AuditChecksState(
    val id: String = "",
    val name: String = "",
    val checkCounter: Int = 0,
    val repetitiveness: String = "",
    val lastTimeChecked: String = "",
    val checks: List<Check> = emptyList(),
    val ongoing: Boolean = false,
    val done: Boolean = false,
    val screenState: ScreenState = ScreenState.LOADING
)