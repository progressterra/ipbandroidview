package com.progressterra.ipbandroidview.pages.catalog

import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.processes.mapper.CatalogMapper
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface CatalogUseCase {

    suspend operator fun invoke(): Result<CatalogCardState>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: IECommerceCoreRepository,
        private val mapper: CatalogMapper
    ) : AbstractTokenUseCase(scrmRepository, provideLocation), CatalogUseCase {

        override suspend fun invoke(): Result<CatalogCardState> = withToken { token ->
            CatalogCardState(children = repo.getCatalog(token).getOrThrow()
                ?.first()?.childItems?.map {
                    mapper.map(
                        it
                    )
                } ?: emptyList())
        }
    }

    class Test : CatalogUseCase {

        override suspend fun invoke(): Result<CatalogCardState> = runCatching {
            val list = listOf(
                CatalogCardState(
                    name = "Хозтовары",
                    imageUrl = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg",
                    children = listOf(
                        CatalogCardState(
                            name = "Хозтовары 1",
                            imageUrl = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                        ), CatalogCardState(
                            name = "Хозтовары 2",
                            imageUrl = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                        ), CatalogCardState(
                            name = "Хозтовары 3",
                            imageUrl = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                        )
                    )
                ), CatalogCardState(
                    name = "Обувь",
                    imageUrl = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg",
                    children = listOf(
                        CatalogCardState(
                            name = "Обувь 1",
                            imageUrl = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                        ), CatalogCardState(
                            name = "Обувь 2",
                            imageUrl = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                        ), CatalogCardState(
                            name = "Обувь 3",
                            imageUrl = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                        )
                    )
                ), CatalogCardState(
                    name = "Еда",
                    imageUrl = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg",
                    children = listOf(
                        CatalogCardState(
                            name = "Еда 1",
                            imageUrl = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                        ), CatalogCardState(
                            name = "Еда 2",
                            imageUrl = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                        ), CatalogCardState(
                            name = "Еда 3",
                            imageUrl = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                        )
                    )
                )
            )
            CatalogCardState(
                children = list
            )
        }
    }
}