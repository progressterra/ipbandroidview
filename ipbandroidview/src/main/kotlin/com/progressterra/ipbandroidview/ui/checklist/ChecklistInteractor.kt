package com.progressterra.ipbandroidview.ui.checklist

interface ChecklistInteractor {

    fun onCheck(check: Check?)

    fun back()

    fun startStopAudit()

    fun yesNo(yes: Boolean)

    fun onCheckCommentaryChange(comment: String)

    fun startPlay()
    
    fun pausePlay()
    
    fun startRecording()
    
    fun stopRecording()

    fun removeRecord()

    fun ready()

    class Empty : ChecklistInteractor {

        override fun onCheck(check: Check?) = Unit

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
    }
}