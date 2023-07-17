package com.progressterra.ipbandroidview.features.wantthiscard

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.entities.Installment
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState

@Immutable
data class WantThisCardState(
    override val id: String = "",
    val image: String = "",
    val oldPrice: SimplePrice = SimplePrice(),
    val price: SimplePrice = SimplePrice(),
    val counter: CounterState = CounterState(),
    val status: TypeStatusDoc = TypeStatusDoc.NOT_FILL,
    val name: String = "",
    val installment: Installment = Installment()
) : Id