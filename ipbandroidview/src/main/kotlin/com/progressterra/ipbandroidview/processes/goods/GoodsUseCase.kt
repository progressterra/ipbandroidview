package com.progressterra.ipbandroidview.processes.goods

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.progressterra.ipbandroidapi.api.product.models.FieldForFilter
import com.progressterra.ipbandroidapi.api.product.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.product.models.SortData
import com.progressterra.ipbandroidview.entities.GoodsFilter
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import kotlinx.coroutines.flow.Flow

interface GoodsUseCase {

    suspend operator fun invoke(filter: GoodsFilter): Result<Flow<PagingData<StoreCardState>>>

    class Base(
        private val fetchGoodsPage: FetchGoodsPage
    ) : GoodsUseCase {

        override suspend fun invoke(filter: GoodsFilter): Result<Flow<PagingData<StoreCardState>>> =
            runCatching {
                Pager(PagingConfig(10)) {
                    GoodsSource(
                        fetchGoodsPage = fetchGoodsPage, filterAndSort = FilterAndSort(
                            listFields = buildList {
                                filter.categoryId?.let {
                                    add(
                                        FieldForFilter(
                                            fieldName = "nomenclature.listCatalogCategory",
                                            listValue = listOf(filter.categoryId),
                                            comparison = "equalsStrong"
                                        )
                                    )
                                }
                                filter.params?.forEach {
                                    add(
                                        FieldForFilter(
                                            fieldName = it.key,
                                            listValue = it.value,
                                            comparison = "containsIgnoreCase"
                                        )
                                    )
                                }
                            }, searchData = filter.search, sort = SortData(
                                fieldName = "name", variantSort = "asc"
                            )
                        )
                    )
                }.flow
            }
    }
}
