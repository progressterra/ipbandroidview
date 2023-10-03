package com.progressterra.ipbandroidview.processes.interests

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.iamhere.models.SortData
import com.progressterra.ipbandroidapi.api.iamhere.models.TypeVariantSort
import com.progressterra.ipbandroidview.entities.Interest
import com.progressterra.ipbandroidview.entities.toInterest
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractSource

class InterestsSource(
    private val service: ImhService,
    private val obtainAccessToken: ObtainAccessToken
) : AbstractSource<Nothing, Interest>() {

    override val pageSize = 40

    override suspend fun loadPage(skip: Int, take: Int): Result<List<Interest>> = runCatching {
        val token = obtainAccessToken().getOrThrow()
        service.clientInterestList(
            token = token,
            body = FilterAndSort(
                listFields = emptyList(),
                sort = SortData(
                    fieldName = "dateAdded",
                    variantSort = TypeVariantSort.DESC
                ),
                searchData = "",
                skip = skip,
                take = take
            )
        ).dataList?.map { it.toInterest() } ?: emptyList()
    }
}