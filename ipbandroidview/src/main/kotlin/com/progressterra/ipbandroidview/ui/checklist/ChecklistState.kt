package com.progressterra.ipbandroidview.ui.checklist

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.composable.component.ButtonState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.AuditDocument
import com.progressterra.ipbandroidview.model.Check

@Immutable
data class ChecklistState(
    val auditDocument: AuditDocument = AuditDocument(),
    val checks: List<Check> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING,
    val currentCheckState: CurrentCheckState = CurrentCheckState(),
    val startButton: ButtonState = ButtonState(),
    val finishButton: ButtonState = ButtonState()
)