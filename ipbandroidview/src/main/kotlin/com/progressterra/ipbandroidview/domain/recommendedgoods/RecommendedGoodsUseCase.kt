package com.progressterra.ipbandroidview.domain.recommendedgoods

import androidx.paging.PagingSource
import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.dto.GoodsCard

interface RecommendedGoodsUseCase {

    fun recommendedGoods(): Result<PagingSource<Int, GoodsCard>>

}