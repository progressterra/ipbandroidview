package com.progressterra.ipbandroidview.usecases.catalog

import com.progressterra.ipbandroidview.data.CommonRepository
import com.progressterra.ipbandroidview.data.IRepository
import com.progressterra.ipbandroidview.usecases.catalog.models.CatalogResponseToUI
import com.progressterra.ipbandroidview.usecases.catalog.models.CatalogUILib
import com.progressterra.ipbandroidview.utils.IUseCase
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.emptyFailed
import com.progressterra.ipbandroidview.utils.extensions.toSuccessResult

internal class GetCatalogLibUseCase : IGetCatalogLibUseCase {
    private val converter = CatalogResponseToUI()
    private val repo: IRepository.Catalog = CommonRepository()

    override suspend fun invoke(): SResult<List<CatalogUILib>> {
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

            if (res.isNotEmpty())
                res.toSuccessResult()
            else
                emptyFailed()
        } else {
            emptyFailed()
        }
    }
}

interface IGetCatalogLibUseCase : IUseCase.Out<SResult<List<CatalogUILib>>> {

    companion object {
        fun IGetCatalogLibUseCase(): IGetCatalogLibUseCase = GetCatalogLibUseCase()
    }
}