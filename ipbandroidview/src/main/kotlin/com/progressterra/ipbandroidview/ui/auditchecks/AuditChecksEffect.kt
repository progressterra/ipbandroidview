package com.progressterra.ipbandroidview.ui.auditchecks

sealed class AuditChecksEffect {

    object OnBack : AuditChecksEffect()
    
    class OnCheck(id: String) : AuditChecksEffect()
}