package com.progressterra.ipbandroidview.processes.goods

import com.progressterra.ipbandroidview.entities.GoodsFilter
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.shared.PagingUseCase

interface GoodsUseCase : PagingUseCase<GoodsFilter, StoreCardState> {

    class Base(
        goodsSource: GoodsSource
    ) : GoodsUseCase, PagingUseCase.Abstract<GoodsFilter, StoreCardState>(goodsSource)
}
