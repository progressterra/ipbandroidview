package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.mapper.SizesMapper
import com.progressterra.ipbandroidview.model.store.GoodsDetails
import com.progressterra.ipbandroidview.model.store.GoodsSize

interface GoodsSizesUseCase {

//    suspend operator fun invoke(goodsDetails: GoodsDetails): Result<List<GoodsSize>>
//
//    class Base(
//        provideLocation: ProvideLocation,
//        scrmRepository: SCRMRepository,
//        private val ieCommerceCoreRepository: IECommerceCoreRepository,
//        private val sizesMapper: SizesMapper
//    ) : AbstractUseCase(scrmRepository, provideLocation), GoodsSizesUseCase {
//
//        override suspend fun invoke(goodsDetails: GoodsDetails): Result<List<GoodsSize>> =
//            withToken {
//                val sizes = ieCommerceCoreRepository.getProductSizeSet(
//                    goodsDetails.artikul,
//                    goodsDetails.idFeature
//                ).getOrThrow()!!.listDataSets ?: emptyList()
//                sizes.map { sizesMapper.map(it) }
//            }
//    }
}