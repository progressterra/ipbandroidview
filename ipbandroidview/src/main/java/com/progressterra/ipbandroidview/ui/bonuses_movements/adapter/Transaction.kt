package com.progressterra.ipbandroidview.ui.bonuses_movements.adapter

import java.util.*

internal data class Transaction(
    val formattedDate: String,
    val quantity: Int,
    val typeBonusName: String,
    val typeOperation: Int,
    val rawDate: Date
)