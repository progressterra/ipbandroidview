package com.progressterra.ipbandroidview.ui.audits

data class Document(
    val id: String,
    val name: String,
    val address: String,
    val percentage: Int,
    val checkCounter: Int,
    val repetitiveness: String,
    val lastTimeChecked: String,
    val done: Boolean
)
