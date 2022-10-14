package com.progressterra.ipbandroidview.ui.checklist

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.composable.VoiceState
import com.progressterra.ipbandroidview.core.ScreenState

@Immutable
data class ChecklistState(
    val id: String = "",
    val name: String = "",
    val checkCounter: Int = 0,
    val repetitiveness: String = "",
    val lastTimeChecked: String = "",
    val currentCheck: Check? = null,
    val checks: List<Check> = emptyList(),
    val ongoing: Boolean = false,
    val done: Boolean = false,
    val sheetVisibility: Boolean = false,
    val voiceState: VoiceState = VoiceState.IDLE
)