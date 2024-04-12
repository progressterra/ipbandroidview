package com.progressterra.ipbandroidview.features.withdrawaltransaction

import com.progressterra.ipbandroidapi.api.payment.models.TypeResultOperationBisinessArea
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.entities.Price


data class WithdrawalTransactionState(
    override val id: String = "",
    val sum: Price = Price(),
    val date: String = "",
    val destination: String = "",
    val status: TypeResultOperationBisinessArea = TypeResultOperationBisinessArea.IN_PROGRESS
) : Id