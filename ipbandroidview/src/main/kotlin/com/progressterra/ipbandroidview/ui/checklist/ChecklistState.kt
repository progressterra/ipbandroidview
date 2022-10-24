package com.progressterra.ipbandroidview.ui.checklist

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.composable.VoiceState
import com.progressterra.ipbandroidview.composable.stats.ChecklistStats
import com.progressterra.ipbandroidview.core.Checklist
import com.progressterra.ipbandroidview.core.ScreenState

@Immutable
data class ChecklistState(
    val currentCheckTitle: String?,
    val currentCheck: Check?,
    val currentCheckMedia: CurrentCheckMedia?,
    val voiceState: VoiceState,
    val checklist: Checklist,
    val changedInSession: Boolean,
    val stats: ChecklistStats,
    val screenState: ScreenState
)