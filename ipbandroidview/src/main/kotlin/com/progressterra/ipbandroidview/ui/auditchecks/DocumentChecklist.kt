package com.progressterra.ipbandroidview.ui.auditchecks

data class DocumentChecklist(
    val name: String,
    val checkCounter: Int,
    val repetitiveness: String,
    val lastTimeChecked: String,
    val checks: List<Check>,
    val state: AuditState = AuditState.NONE
)