package com.progressterra.ipbandroidview.ui.checklist

interface ChecklistInteractor {

    fun onCheck(check: Check)

    fun onSheetVisibilityChange(visibility: Boolean)

    fun back()

    fun startStopAudit()

    fun closeSheet()

    fun yesNo(yes: Boolean)

    fun onCheckCommentaryChange(comment: String)

    fun startPauseVoicePlay()

    fun startStopVoiceRecording()

    fun removeRecord()

    fun ready()

    class Empty : ChecklistInteractor {

        override fun onSheetVisibilityChange(visibility: Boolean) = Unit

        override fun onCheck(check: Check) = Unit

        override fun back() = Unit

        override fun startStopAudit() = Unit

        override fun closeSheet() = Unit

        override fun yesNo(yes: Boolean) = Unit

        override fun onCheckCommentaryChange(comment: String) = Unit

        override fun startPauseVoicePlay() = Unit

        override fun startStopVoiceRecording() = Unit

        override fun removeRecord() = Unit

        override fun ready() = Unit
    }
}