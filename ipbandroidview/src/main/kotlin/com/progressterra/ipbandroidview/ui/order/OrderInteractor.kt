package com.progressterra.ipbandroidview.ui.order

import com.progressterra.ipbandroidview.composable.component.ReceiptComponentEvent
import com.progressterra.ipbandroidview.model.Delivery
import com.progressterra.ipbandroidview.model.PaymentType

interface OrderInteractor : ReceiptComponentEventHandler {

    fun onBack()

    fun onGoodsDetails(goodsId: String)

    fun changeAddress()

    fun selectPickUpPoint()

    fun selectDeliveryMethod(delivery: Delivery)

    fun selectPayment(paymentType: PaymentType)

    fun editComment(comment: String)

    fun changeUseBonuses(useBonuses: Boolean)

    fun changeReceiveReceipt(receiveReceive: Boolean)

    fun editEmail(email: String)

    fun refresh()

    class Empty : OrderInteractor {

        override fun handleEvent(event: ReceiptComponentEvent) = Unit

        override fun onBack() = Unit

        override fun onGoodsDetails(goodsId: String) = Unit

        override fun changeAddress() = Unit

        override fun selectPickUpPoint() = Unit

        override fun selectDeliveryMethod(delivery: Delivery) = Unit

        override fun selectPayment(paymentType: PaymentType) = Unit

        override fun editComment(comment: String) = Unit

        override fun changeUseBonuses(useBonuses: Boolean) = Unit

        override fun changeReceiveReceipt(receiveReceive: Boolean) = Unit

        override fun editEmail(email: String) = Unit

        override fun refresh() = Unit
    }
}