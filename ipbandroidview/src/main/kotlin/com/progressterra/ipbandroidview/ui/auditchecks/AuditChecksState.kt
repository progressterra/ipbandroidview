package com.progressterra.ipbandroidview.ui.auditchecks

import androidx.compose.runtime.Immutable

@Immutable
data class AuditChecksState(
    val name: String = "",
    val checkCounter: Int = 0,
    val repetitiveness: String = "",
    val lastTimeChecked: String = "",
    val checks: List<Check> = emptyList(),
    val state: AuditState = AuditState.NONE
)