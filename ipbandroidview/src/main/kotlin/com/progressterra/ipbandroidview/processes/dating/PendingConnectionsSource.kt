package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.FieldForFilter
import com.progressterra.ipbandroidapi.api.iamhere.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.iamhere.models.SortData
import com.progressterra.ipbandroidapi.api.iamhere.models.TypeComparison
import com.progressterra.ipbandroidapi.api.iamhere.models.TypeVariantSort
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.entities.toDatingUser
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractSource

class PendingConnectionsSource(
    private val obtainAccessToken: ObtainAccessToken,
    private val service: ImhService
) : AbstractSource<Nothing, DatingUser>() {

    override val pageSize: Int = 6

    override suspend fun loadPage(skip: Int, take: Int): Result<List<DatingUser>> = runCatching {
        val token = obtainAccessToken().getOrThrow()
        service.connectsOutcoming(
            token = token,
            body = FilterAndSort(
                listFields = listOf(
                    FieldForFilter(
                        fieldName = "statusConnect",
                        listValue = listOf("wait"),
                        comparison = TypeComparison.EQUALS_STRONG
                    )
                ),
                sort = SortData(
                    fieldName = "dateAdded",
                    variantSort = TypeVariantSort.DESC
                ),
                searchData = "",
                skip = skip, take = take
            )
        ).dataList?.map {
            it.toDatingUser(true)
        } ?: emptyList()
    }
}