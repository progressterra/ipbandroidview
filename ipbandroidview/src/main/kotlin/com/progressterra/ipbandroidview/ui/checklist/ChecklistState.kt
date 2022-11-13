package com.progressterra.ipbandroidview.ui.checklist

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.components.VoiceState
import com.progressterra.ipbandroidview.components.stats.ChecklistStats
import com.progressterra.ipbandroidview.core.ScreenState

@Immutable
data class ChecklistState(
    val currentCheck: Check? = null,
    val currentCheckMedia: CurrentCheckMedia? = null,
    val voiceState: VoiceState = VoiceState.Player(false, 0f),
    val stats: ChecklistStats = ChecklistStats(0, 0, 0, 0),
    val done: Boolean = false,
    val ongoing: Boolean = false,
    val id: String = "",
    val placeId: String = "",
    val name: String = "",
    val checks: List<Check> = emptyList(),
    val isDocument: Boolean = false,
    val checkScreenState: ScreenState = ScreenState.LOADING,
    val checklistScreenState: ScreenState = ScreenState.LOADING
)