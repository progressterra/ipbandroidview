package com.progressterra.ipbandroidview.ui.auditchecks

interface AuditChecksInteractor {

    fun onCheck(check: Check)

    fun onBack()

    fun onStart()

    fun onStop()

    class Empty : AuditChecksInteractor {

        override fun onCheck(check: Check) = Unit

        override fun onBack() = Unit

        override fun onStart() = Unit

        override fun onStop() = Unit
    }
}