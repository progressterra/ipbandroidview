package com.progressterra.ipbandroidview.pages.orderlist

import com.progressterra.ipbandroidview.features.ordercompact.OrderCompactState
import com.progressterra.ipbandroidview.shared.PagingUseCase

interface OrdersUseCase : PagingUseCase<Nothing, OrderCompactState> {

    class Base(
        ordersSource: OrdersSource
    ) : OrdersUseCase, PagingUseCase.Abstract<Nothing, OrderCompactState>(ordersSource)
}