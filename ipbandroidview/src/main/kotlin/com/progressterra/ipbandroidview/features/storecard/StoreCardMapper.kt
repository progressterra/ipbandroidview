package com.progressterra.ipbandroidview.features.storecard

import com.google.gson.Gson
import com.progressterra.ipbandroidapi.api.product.models.ProductView
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.AbstractMapper
import com.progressterra.ipbandroidview.shared.Mapper

interface StoreCardMapper : Mapper<ProductView, StoreCardState> {

    class Base(
        gson: Gson
    ) : StoreCardMapper, AbstractMapper(gson) {

        override fun map(data: ProductView): StoreCardState = StoreCardState(
            name = data.nomenclature?.name ?: "",
            price = SimplePrice(data.inventoryData?.currentPrice ?: 0.0),
            imageUrl = data.nomenclature?.listImages?.first()?.urlData ?: "",
            loan = "Рассрочка: ${data.installmentPlanValue?.countMonthPayment} платежей по ${
                SimplePrice(
                    data.installmentPlanValue?.amountPaymentInMonth ?: 0.0
                )
            }"
        )
    }
}