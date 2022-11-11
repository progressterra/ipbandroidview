package com.progressterra.ipbandroidview.ui.checklist

import com.progressterra.ipbandroidview.actions.Back
import com.progressterra.ipbandroidview.actions.Refresh
import com.progressterra.ipbandroidview.actions.Remove
import com.progressterra.ipbandroidview.model.CheckPicture

interface ChecklistInteractor : Back, Refresh, Remove {

    fun openCheck(check: Check)

    fun startStopAudit()

    fun yesNo(yes: Boolean)

    fun editCheckCommentary(comment: String)

    fun startPausePlay()

    fun startStopRecording()

    fun applyCheck()

    fun openImage(picture: CheckPicture)

    fun onCamera()

    class Empty : ChecklistInteractor {

        override fun openCheck(check: Check) = Unit

        override fun back() = Unit

        override fun refresh() = Unit

        override fun startStopAudit() = Unit

        override fun yesNo(yes: Boolean) = Unit

        override fun editCheckCommentary(comment: String) = Unit

        override fun startPausePlay() = Unit

        override fun startStopRecording() = Unit

        override fun remove() = Unit

        override fun applyCheck() = Unit

        override fun openImage(picture: CheckPicture) = Unit

        override fun onCamera() = Unit
    }
}