package com.progressterra.ipbandroidview.model.component

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.model.StoreGoods

@Immutable
interface SearchGoods {

    val searchGoods: List<StoreGoods>
}