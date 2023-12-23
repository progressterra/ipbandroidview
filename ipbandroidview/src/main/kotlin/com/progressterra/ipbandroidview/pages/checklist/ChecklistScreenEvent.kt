package com.progressterra.ipbandroidview.pages.checklist

import com.progressterra.ipbandroidview.entities.Check
import com.progressterra.ipbandroidview.entities.MultisizedImage

sealed class ChecklistScreenEvent {

    data class OnCheck(val check: Check) : ChecklistScreenEvent()

    data class OnImage(val image: MultisizedImage) : ChecklistScreenEvent()

    data class YesNo(val yesNo: Boolean) : ChecklistScreenEvent()

    data object StartStopRecording : ChecklistScreenEvent()

    data object StartPausePlay : ChecklistScreenEvent()

    data object OpenCamera : ChecklistScreenEvent()

    data object RemoveVoice : ChecklistScreenEvent()
}