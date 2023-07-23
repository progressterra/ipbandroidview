package com.progressterra.ipbandroidview.pages.catalog

import com.progressterra.ipbandroidapi.api.catalog.CatalogRepository
import com.progressterra.ipbandroidapi.api.catalog.models.FilterAndSort
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
        private val repo: CatalogRepository,
        private val mapper: CatalogMapper
    ) : AbstractTokenUseCase(scrmRepository, provideLocation), CatalogUseCase {

        override suspend fun invoke(): Result<CatalogCardState> = withToken { token ->
            CatalogCardState(children = repo.catalog(
                token,
                FilterAndSort(
                    emptyList(), null, "", 0, 300
                )
            ).getOrThrow()?.listChildItems?.map { mapper.map(it) } ?: emptyList())
        }
    }

    class Test : CatalogUseCase {

        override suspend fun invoke(): Result<CatalogCardState> = runCatching {
            val list = listOf(
                CatalogCardState(
                    name = "Хозтовары",
                    image = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg",
                    children = listOf(
                        CatalogCardState(
                            name = "Хозтовары 1",
                            image = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                        ), CatalogCardState(
                            name = "Хозтовары 2",
                            image = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                        ), CatalogCardState(
                            name = "Хозтовары 3",
                            image = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                        )
                    )
                ), CatalogCardState(
                    name = "Обувь",
                    image = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg",
                    children = listOf(
                        CatalogCardState(
                            name = "Обувь 1",
                            image = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                        ), CatalogCardState(
                            name = "Обувь 2",
                            image = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                        ), CatalogCardState(
                            name = "Обувь 3",
                            image = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                        )
                    )
                ), CatalogCardState(
                    name = "Еда",
                    image = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg",
                    children = listOf(
                        CatalogCardState(
                            name = "Еда 1",
                            image = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                        ), CatalogCardState(
                            name = "Еда 2",
                            image = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                        ), CatalogCardState(
                            name = "Еда 3",
                            image = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
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