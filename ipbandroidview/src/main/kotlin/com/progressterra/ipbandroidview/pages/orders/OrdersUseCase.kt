package com.progressterra.ipbandroidview.pages.orders

import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.IPBFavPromoRecRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.model.TypeEntities
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.storecard.StoreCardMapper
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractUseCase

interface OrdersUseCase {

    suspend operator fun invoke(): Result<List<StoreCardState>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val favoriteRepository: IPBFavPromoRecRepository,
        private val eIECommerceCoreRepository: IECommerceCoreRepository,
        private val mapper: StoreCardMapper
    ) : AbstractUseCase(scrmRepository, provideLocation), OrdersUseCase {

        override suspend fun invoke(): Result<List<StoreCardState>> = withToken { token ->
            val favoriteIds = favoriteRepository.getClientEntityByType(
                token, TypeEntities.ONE.ordinal
            ).getOrThrow()!!
            buildList {
                favoriteIds.map { favoriteId ->
                    eIECommerceCoreRepository.getProductDetailByIDRG(
                        favoriteId
                    ).getOrThrow()?.listProducts?.firstOrNull()?.let {
                        add(mapper.map(it))
                    }
                }
            }
        }
    }
}