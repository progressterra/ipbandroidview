package com.progressterra.ipbandroidview.ui.checklist

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.composable.VoiceState
import com.progressterra.ipbandroidview.composable.stats.ChecklistStats
import com.progressterra.ipbandroidview.core.Checklist
import com.progressterra.ipbandroidview.core.ScreenState

@Immutable
data class ChecklistState(
    val currentCheck: Check? = null,
    val voiceState: VoiceState = VoiceState.Recorder(false),
    val checklist: Checklist,
    val stats: ChecklistStats
)