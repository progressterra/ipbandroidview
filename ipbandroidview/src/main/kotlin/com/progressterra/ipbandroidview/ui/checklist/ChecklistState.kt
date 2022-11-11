package com.progressterra.ipbandroidview.ui.checklist

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.components.StateBoxState
import com.progressterra.ipbandroidview.components.VoiceState
import com.progressterra.ipbandroidview.components.stats.ChecklistStats
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.Checklist

@Immutable
data class ChecklistState(
    val currentCheck: Check?,
    val currentCheckMedia: CurrentCheckMedia?,
    val voiceState: VoiceState,
    val checklist: Checklist,
    val stats: ChecklistStats,
    override val screenState: ScreenState
) : StateBoxState