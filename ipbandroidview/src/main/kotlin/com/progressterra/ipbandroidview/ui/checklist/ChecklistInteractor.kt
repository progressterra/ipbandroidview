package com.progressterra.ipbandroidview.ui.checklist

interface ChecklistInteractor {

    fun onCheck(check: Check?)

    fun back()

    fun startStopAudit()

    fun yesNo(yes: Boolean)

    fun onCheckCommentaryChange(comment: String)

    fun startPauseVoicePlay()

    fun startStopVoiceRecording()

    fun removeRecord()

    fun ready()

    class Empty : ChecklistInteractor {

        override fun onCheck(check: Check?) = Unit

        override fun back() = Unit

        override fun startStopAudit() = Unit

        override fun yesNo(yes: Boolean) = Unit

        override fun onCheckCommentaryChange(comment: String) = Unit

        override fun startPauseVoicePlay() = Unit

        override fun startStopVoiceRecording() = Unit

        override fun removeRecord() = Unit

        override fun ready() = Unit
    }
}