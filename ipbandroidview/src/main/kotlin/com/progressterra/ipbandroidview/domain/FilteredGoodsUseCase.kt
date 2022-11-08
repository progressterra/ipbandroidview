package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.ipbfavpromorec.IPBFavPromoRecRepository
import com.progressterra.ipbandroidapi.api.ipbfavpromorec.model.TypeOfEntity
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.product.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.product.models.IncomeDataForFilterAndSort
import com.progressterra.ipbandroidapi.api.product.models.SortData
import com.progressterra.ipbandroidapi.api.product.models.TypeVariantSort
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.domain.mapper.GoodsFilterMapper
import com.progressterra.ipbandroidview.domain.mapper.GoodsMapper
import com.progressterra.ipbandroidview.dto.Filter
import com.progressterra.ipbandroidview.dto.Goods

interface FilteredGoodsUseCase {

    suspend fun goods(id: String, keyword: String, filters: List<Filter>): Result<List<Goods>>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val repo: ProductRepository,
        private val favoriteRepository: IPBFavPromoRecRepository,
        private val filterMapper: GoodsFilterMapper,
        private val goodsMapper: GoodsMapper
    ) : FilteredGoodsUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun goods(
            id: String,
            keyword: String,
            filters: List<Filter>
        ): Result<List<Goods>> = runCatching {
            val favorites = withToken {
                favoriteRepository.getClientEntityByType(
                    it, TypeOfEntity.PRODUCT
                )
            }.getOrThrow()
            repo.itemsFiltered(
                IncomeDataForFilterAndSort(
                    filterAndSortData = FilterAndSort(
                        listFields = filters.map { filterMapper.map(it) }, sort = SortData(
                            fieldName = "price", variantSort = TypeVariantSort.ASC
                        ),
                        searchString = keyword
                    ), idCategory = id
                )
            ).getOrThrow()?.listProducts?.map {
                goodsMapper.map(it, favorites)
            } ?: emptyList()
        }
    }
}