package com.progressterra.ipbandroidview.domain.usecase

import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.model.GoodsDetails

interface GoodsDetailsUseCase {

    suspend fun goodsDetails(id: String): Result<GoodsDetails>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val ieCommerceCoreRepository: IECommerceCoreRepository
    ) : AbstractUseCase(scrmRepository, provideLocation), GoodsDetailsUseCase {

        override suspend fun goodsDetails(id: String): Result<GoodsDetails> {
            TODO("Not yet implemented")
        }
    }
}