package com.progressterra.ipbandroidview.data.model

data class Transaction(
    val dateEvent: String,
    val quantity: Int,
    val typeBonusName: String,
    val typeOperation: Int
)