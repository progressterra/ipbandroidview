package com.progressterra.ipbandroidview.model.partner

import com.progressterra.ipbandroidview.model.Id
import java.util.Date

data class Offer(
    override val id: String,
    val partnerId: String,
    val categoryId: String,
    val deadlineDate: Date,
    val title: String,
    val headDescription: String,
    val fullDescription: String,
    val forOwn: Boolean,
    val enrollBonuses: Double,
    var favorite: Boolean,
    val imageUrl: String? = null
) : Id
