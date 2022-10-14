package com.progressterra.ipbandroidview.core

import com.progressterra.ipbandroidview.ui.checklist.Check

data class Checklist(
    val id: String,
    val name: String,
    val repetitiveness: String,
    val lastTimeChecked: String,
    val checks: List<Check>
)
