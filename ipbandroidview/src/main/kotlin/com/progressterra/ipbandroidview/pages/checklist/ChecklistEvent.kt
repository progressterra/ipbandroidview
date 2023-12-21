package com.progressterra.ipbandroidview.pages.checklist

import com.progressterra.ipbandroidview.entities.Check
import com.progressterra.ipbandroidview.entities.MultisizedImage

sealed class ChecklistEvent {

    data class OnCheck(val check: Check) : ChecklistEvent()

    data class OnImage(val image: MultisizedImage) : ChecklistEvent()

    data class YesNo(val yesNo: Boolean) : ChecklistEvent()

    data object StartStopRecording : ChecklistEvent()

    data object StartPausePlay : ChecklistEvent()

    data object OpenCamera : ChecklistEvent()

    data object RemoveVoice : ChecklistEvent()
}