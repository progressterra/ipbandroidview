package com.progressterra.ipbandroidview.ui.checklist

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.composable.component.VoiceState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.AuditDocument
import com.progressterra.ipbandroidview.model.Check
import com.progressterra.ipbandroidview.model.ChecklistStats

@Immutable
data class ChecklistState(
    val currentCheck: Check? = null,
    val currentCheckMedia: CurrentCheckMedia? = null,
    val voiceState: VoiceState = VoiceState.Recorder(false),
    val stats: ChecklistStats = ChecklistStats(0, 0, 0, 0),
    val auditDocument: AuditDocument = AuditDocument(),
    val checks: List<Check> = emptyList(),
    val checkScreenState: ScreenState = ScreenState.LOADING,
    val checklistScreenState: ScreenState = ScreenState.LOADING
)