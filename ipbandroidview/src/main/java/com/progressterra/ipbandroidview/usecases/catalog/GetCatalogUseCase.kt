package com.progressterra.ipbandroidview.usecases.catalog

import com.progressterra.ipbandroidapi.exception.BadRequestException
import com.progressterra.ipbandroidview.data.IRepository
import com.progressterra.ipbandroidview.usecases.catalog.models.CatalogResponseToUI
import com.progressterra.ipbandroidview.usecases.catalog.models.CatalogUILib
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.emptyFailed
import com.progressterra.ipbandroidview.utils.extensions.toSuccessResult

interface GetCatalogUseCase {

    suspend fun getCatalog(): Result<List<CatalogUILib>>

    class Base(
        private val repo: IRepository.Catalog
    ) : GetCatalogUseCase {
        private val converter = CatalogResponseToUI()

        override suspend fun getCatalog(): Result<List<CatalogUILib>> {
            val mainCatalogs = repo.getCatalog()
            return if (mainCatalogs is SResult.Success) {

                val res = mainCatalogs.data.mapNotNull {
                    val name = it.item?.name
                    val categories = converter.convert(it.childItems)
                    if (name != null && categories != null)
                        CatalogUILib(
                            title = it.item?.name ?: "",
                            categories = converter.convert(it.childItems) ?: emptyList()
                        )
                    else
                        null
                }
                Result.success(res)
            } else {
                Result.failure(BadRequestException())
            }
        }
    }
}