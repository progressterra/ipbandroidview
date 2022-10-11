package com.progressterra.ipbandroidview.ui.auditchecks

data class Check(
    val state: CheckState,
    val id: String,
    val category: String,
    val name: String,
)
