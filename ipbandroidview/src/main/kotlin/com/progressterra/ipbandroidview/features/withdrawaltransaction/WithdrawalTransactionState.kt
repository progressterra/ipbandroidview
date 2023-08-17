package com.progressterra.ipbandroidview.features.withdrawaltransaction

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidapi.api.payment.models.TypeResultOperationBisinessArea
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.entities.SimplePrice

@Immutable
data class WithdrawalTransactionState(
    override val id: String = "",
    val sum: SimplePrice = SimplePrice(),
    val date: String = "",
    val destination: String = "",
    val status: TypeResultOperationBisinessArea = TypeResultOperationBisinessArea.IN_PROGRESS
) : Id