package com.progressterra.ipbandroidview.domain.usecase.store

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
import com.progressterra.ipbandroidview.domain.mapper.StoreGoodsMapper
import com.progressterra.ipbandroidview.model.Filter
import com.progressterra.ipbandroidview.model.StoreGoods

interface FilteredGoodsUseCase {

    suspend operator fun invoke(id: String, keyword: String, filters: List<Filter>): Result<List<StoreGoods>>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val repo: ProductRepository,
        private val favoriteRepository: IPBFavPromoRecRepository,
        private val filterMapper: GoodsFilterMapper,
        private val storeGoodsMapper: StoreGoodsMapper
    ) : FilteredGoodsUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(
            id: String,
            keyword: String,
            filters: List<Filter>
        ): Result<List<StoreGoods>> = withToken { token ->
            val favorites = favoriteRepository.getClientEntityByType(
                token, TypeOfEntity.PRODUCT
            ).getOrThrow()
            repo.itemsFiltered(
                IncomeDataForFilterAndSort(
                    filterAndSortData = FilterAndSort(
                        listFields = filters.map { filterMapper.map(it) }, sort = SortData(
                            fieldName = "newCollection", variantSort = TypeVariantSort.ASC
                        ),
                        searchString = keyword.trim()
                    ), idCategory = id
                )
            ).getOrThrow()?.listProducts?.map {
                storeGoodsMapper.map(it, favorites.contains(it.idUnique!!))
            } ?: emptyList()
        }
    }
}