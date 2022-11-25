package com.progressterra.ipbandroidview.domain.mapper

import com.progressterra.ipbandroidapi.api.iecommerce.model.StatusOrder
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.Mapper

interface StatusOrderMapper : Mapper<StatusOrder, String> {

    class Base(
        private val manageResources: ManageResources
    ) : StatusOrderMapper {

        override fun map(data: StatusOrder): String = when (data) {
            StatusOrder.ORDER -> manageResources.string(R.string.order_confirmed)
            StatusOrder.CONFIRM_FROM_STORE -> manageResources.string(R.string.order_confirm_from_store)
            StatusOrder.CONFIRM_FROM_CALL_CENTER -> manageResources.string(R.string.order_confirm_from_call_center)
            StatusOrder.SENT_TO_WAREHOUSE -> manageResources.string(R.string.order_sent_to_warehouse)
            StatusOrder.SENT_DELIVERY_SERVICE -> manageResources.string(R.string.order_sent_delivery_service)
            StatusOrder.ON_PICK_UP_POINT -> manageResources.string(R.string.order_on_pick_point)
            StatusOrder.DELIVERED -> manageResources.string(R.string.order_delivered)
            StatusOrder.CANCELED -> manageResources.string(R.string.order_canceled)
        }
    }
}