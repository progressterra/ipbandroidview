package com.progressterra.ipbandroidview.ui.checklist

import com.progressterra.ipbandroidview.actions.Back
import com.progressterra.ipbandroidview.actions.Refresh
import com.progressterra.ipbandroidview.core.Picture

interface ChecklistInteractor : Back, Refresh {

    fun check(check: Check)

    fun closeCheck()

    fun startStopAudit()

    fun yesNo(yes: Boolean)

    fun onCheckCommentaryChange(comment: String)

    fun startPausePlay()

    fun startStopRecording()

    fun removeRecord()

    fun applyCheck()

    fun openImage(picture: Picture)

    fun onCamera()

    class Empty : ChecklistInteractor {

        override fun back() = Unit

        override fun refresh() = Unit

        override fun check(check: Check) = Unit

        override fun closeCheck() = Unit

        override fun startStopAudit() = Unit

        override fun yesNo(yes: Boolean) = Unit

        override fun onCheckCommentaryChange(comment: String) = Unit

        override fun startPausePlay() = Unit

        override fun startStopRecording() = Unit

        override fun removeRecord() = Unit

        override fun applyCheck() = Unit

        override fun openImage(picture: Picture) = Unit

        override fun onCamera() = Unit
    }
}