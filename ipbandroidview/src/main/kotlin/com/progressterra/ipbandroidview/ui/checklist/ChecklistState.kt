package com.progressterra.ipbandroidview.ui.checklist

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.composable.VoiceState
import com.progressterra.ipbandroidview.composable.stats.ChecklistStats
import com.progressterra.ipbandroidview.core.Checklist

@Immutable
data class ChecklistState(
    val currentCheck: Check?,
    val voiceState: VoiceState,
    val checklist: Checklist,
    val stats: ChecklistStats,
    val photos: List<String>
)