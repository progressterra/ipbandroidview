package com.progressterra.ipbandroidview.ui.auditchecks

import androidx.compose.runtime.Immutable
import java.util.Locale.Category

@Immutable
data class AuditChecksState(
    val name: String,
    val checkCounter: Int,
    val repetitiveness: String,
    val lastTimeChecked: String,
    val categories: List<Category>,
    val state: AuditState = AuditState.NONE
)