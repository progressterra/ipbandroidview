package com.progressterra.ipbandroidview.features.bankcard

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.entities.Id

@Immutable
data class BankCardState(
    override val id: String = "",
    val isMainCard: Boolean = false,
    val document: Document = Document(),
    val isSelected: Boolean = false
) : Id