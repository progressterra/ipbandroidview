package com.progressterra.ipbandroidview.ui.checklist

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.composable.VoiceState
import com.progressterra.ipbandroidview.composable.stats.ChecklistStats
import com.progressterra.ipbandroidview.core.Checklist
import com.progressterra.ipbandroidview.core.Photo
import com.progressterra.ipbandroidview.core.ScreenState

@Immutable
data class ChecklistState(
    val currentCheck: Check?,
    val currentCheckDetails: CurrentCheckDetails?,
    val voiceState: VoiceState,
    val checklist: Checklist,
    val stats: ChecklistStats,
    val photos: List<Photo>,
    val screenState: ScreenState,
    val voiceEditable: Boolean
)