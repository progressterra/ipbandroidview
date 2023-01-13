package com.progressterra.ipbandroidview.ui.checklist

import com.progressterra.ipbandroidview.model.checklist.Check
import com.progressterra.ipbandroidview.model.media.MultisizedImage

interface ChecklistInteractor {

    fun onBack()

    fun refreshCheck()

    fun refreshChecklist()

    fun openCheck(check: Check)

    fun applyCheck()

    fun startStopAudit()

    fun yesNo(yesNo: Boolean)

    fun editCheckCommentary(commentary: String)

    fun startPausePlay()

    fun startStopRecording()

    fun remove()

    fun openImage(image: MultisizedImage)

    fun onCamera()

    class Empty : ChecklistInteractor {

        override fun onBack() = Unit

        override fun refreshCheck() = Unit

        override fun refreshChecklist() = Unit

        override fun openCheck(check: Check) = Unit

        override fun applyCheck() = Unit

        override fun startStopAudit() = Unit

        override fun yesNo(yesNo: Boolean) = Unit

        override fun editCheckCommentary(commentary: String) = Unit

        override fun startPausePlay() = Unit

        override fun startStopRecording() = Unit

        override fun remove() = Unit

        override fun openImage(image: MultisizedImage) = Unit

        override fun onCamera() = Unit
    }
}