package com.progressterra.ipbandroidview.model

import com.progressterra.ipbandroidapi.api.ipbdelivery.models.DeliveryMethondTypeEnum

enum class DeliveryMethodId(val value: String) {
    EMPTY(""),
    COURIER("91c316b0-3b22-4f6f-a006-36ba5331a08c"),
    PVZ("f10dc8a0-1ebf-40be-b6a6-c85cc48f2625"),
    POSTAMAT("70d5a466-d4b1-47ab-9304-cba271f613b3");


    fun toServiceEnum(): DeliveryMethondTypeEnum = when (this) {
        PVZ -> DeliveryMethondTypeEnum.ONE
        POSTAMAT -> DeliveryMethondTypeEnum.TWO
        else -> DeliveryMethondTypeEnum.ZERO
    }
}
