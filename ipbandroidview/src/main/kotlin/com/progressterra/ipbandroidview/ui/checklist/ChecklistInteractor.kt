package com.progressterra.ipbandroidview.ui.checklist

import com.progressterra.ipbandroidview.actions.Back
import com.progressterra.ipbandroidview.actions.OpenDetails
import com.progressterra.ipbandroidview.actions.Refresh
import com.progressterra.ipbandroidview.actions.Remove
import com.progressterra.ipbandroidview.core.Picture

interface ChecklistInteractor : Back, Refresh, Remove, OpenDetails<Check> {

    fun closeCheck()

    fun startStopAudit()

    fun yesNo(yes: Boolean)

    fun editCheckCommentary(comment: String)

    fun startPausePlay()

    fun startStopRecording()

    fun applyCheck()

    fun openImage(picture: Picture)

    fun onCamera()

    class Empty : ChecklistInteractor {

        override fun back() = Unit

        override fun refresh() = Unit

        override fun openDetails(key: Check) = Unit

        override fun closeCheck() = Unit

        override fun startStopAudit() = Unit

        override fun yesNo(yes: Boolean) = Unit

        override fun editCheckCommentary(comment: String) = Unit

        override fun startPausePlay() = Unit

        override fun startStopRecording() = Unit

        override fun remove() = Unit

        override fun applyCheck() = Unit

        override fun openImage(picture: Picture) = Unit

        override fun onCamera() = Unit
    }
}