package com.progressterra.ipbandroidview.features.bankcard

import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.entities.Id


data class BankCardState(
    override val id: String = "",
    val isMainCard: Boolean = false,
    val document: Document = Document(),
    val isSelected: Boolean = false
) : Id