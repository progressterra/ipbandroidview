package com.progressterra.ipbandroidview.dto.component

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.dto.Goods
import kotlinx.coroutines.flow.Flow

interface SearchGoodsFlow {
    val searchGoods: Flow<PagingData<Goods>>
}