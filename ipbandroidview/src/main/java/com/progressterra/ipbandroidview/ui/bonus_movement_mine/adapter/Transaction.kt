package com.progressterra.ipbandroidview.ui.bonus_movement_mine.adapter

import java.util.*

data class Transaction(
    val formattedDate: String,
    val quantity: Int,
    val typeBonusName: String,
    val typeOperation: Int,
    val rawDate: Date?
){
    companion object{

    }
}