package com.progressterra.ipbandroidview.domain.recommendedgoods

import android.util.Log
import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.IPBFavPromoRecRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.model.TypeOfEntity
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.DomainConstants
import com.progressterra.ipbandroidview.domain.mapper.GoodsCardMapper
import com.progressterra.ipbandroidview.dto.GoodsCard

interface GoodsPageUseCase {

    suspend fun goodsPage(idCategory: String, pageNumber: Int): Result<Pair<Int, List<GoodsCard>>>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val mapper: GoodsCardMapper,
        private val eCommerceRepo: IECommerceCoreRepository,
        private val favoriteRepository: IPBFavPromoRecRepository
    ) : AbstractUseCase(scrmRepository, provideLocation), GoodsPageUseCase {

        override suspend fun goodsPage(
            idCategory: String, pageNumber: Int
        ): Result<Pair<Int, List<GoodsCard>>> = runCatching {
            Log.d("PAGING", "category $idCategory, page $pageNumber")
            val favorites = withToken {
                favoriteRepository.getClientEntityByType(
                    it, TypeOfEntity.PRODUCT
                )
            }.getOrThrow()
            Log.d("PAGING", "favorites $favorites")
            val result = withToken {
                eCommerceRepo.getProductsByCategory(
                    it,
                    DomainConstants.MAIN_DEFAULT_CATEGORY_ID,
                    pageNumber,
                    DomainConstants.PAGE_SIZE,
                    0,
                    0
                )
            }.getOrThrow()
            Log.d("PAGING", "favorites $result")
            result?.numberCurrentPage!! to result.listProducts!!.map { mapper.map(it, favorites) }
        }
    }
}