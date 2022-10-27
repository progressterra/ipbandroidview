package com.progressterra.ipbandroidview.ui.checklist

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.components.VoiceState
import com.progressterra.ipbandroidview.components.stats.ChecklistStats
import com.progressterra.ipbandroidview.core.Checklist
import com.progressterra.ipbandroidview.core.ScreenState

@Immutable
data class ChecklistState(
    val currentCheck: Check?,
    val currentCheckMedia: CurrentCheckMedia?,
    val voiceState: VoiceState,
    val checklist: Checklist,
    val stats: ChecklistStats,
    val screenState: ScreenState
)