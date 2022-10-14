package com.progressterra.ipbandroidview.ui.auditchecks

interface AuditChecksInteractor {

    fun onRefresh()

    fun onCheck(check: Check)

    fun onSheetVisibilityChange(visibility: Boolean)

    fun onBack()

    fun onStart()

    fun onStop()

    fun closeSheet()

    fun yesNo(yes: Boolean)

    fun onCheckCommentaryChange(commentary: String)

    class Empty : AuditChecksInteractor {

        override fun onSheetVisibilityChange(visibility: Boolean) = Unit

        override fun onCheck(check: Check) = Unit

        override fun onBack() = Unit

        override fun onStart() = Unit

        override fun onStop() = Unit

        override fun onRefresh() = Unit

        override fun closeSheet() = Unit

        override fun yesNo(yes: Boolean) = Unit

        override fun onCheckCommentaryChange(commentary: String) {
            TODO("Not yet implemented")
        }
    }
}