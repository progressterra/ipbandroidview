package com.progressterra.ipbandroidview.ui.auditchecks

sealed class AuditChecksEffect {

    object OnBack : AuditChecksEffect()
    
    class OnCheck(val check: Check) : AuditChecksEffect()
}