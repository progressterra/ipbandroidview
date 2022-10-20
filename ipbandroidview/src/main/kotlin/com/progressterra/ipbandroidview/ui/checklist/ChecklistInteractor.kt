package com.progressterra.ipbandroidview.ui.checklist

import com.progressterra.ipbandroidview.actions.Back
import com.progressterra.ipbandroidview.actions.Refresh
import com.progressterra.ipbandroidview.core.Photo

interface ChecklistInteractor : Back, Refresh {

    fun onCheck(check: Check)

    fun startStopAudit()

    fun yesNo(yes: Boolean)

    fun onCheckCommentaryChange(comment: String)

    fun startPlay()

    fun pausePlay()

    fun startRecording()

    fun stopRecording()

    fun removeRecord()

    fun ready()

    fun openImage(photo: Photo)

    fun onCamera()

    class Empty : ChecklistInteractor {

        override fun refresh() = Unit

        override fun openImage(photo: Photo) = Unit

        override fun onCheck(check: Check) = Unit

        override fun back() = Unit

        override fun startStopAudit() = Unit

        override fun yesNo(yes: Boolean) = Unit

        override fun onCheckCommentaryChange(comment: String) = Unit

        override fun startPlay() = Unit

        override fun pausePlay() = Unit

        override fun startRecording() = Unit

        override fun stopRecording() = Unit

        override fun removeRecord() = Unit

        override fun ready() = Unit

        override fun onCamera() = Unit
    }
}