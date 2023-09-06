package com.progressterra.ipbandroidview.pages.favorites

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface FavoriteGoodsUseCase {

    suspend operator fun invoke(): Result<Flow<PagingData<StoreCardState>>>

    class Base : FavoriteGoodsUseCase {

        override suspend fun invoke(): Result<Flow<PagingData<StoreCardState>>> =
            Result.success(emptyFlow())
    }
}