package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.FieldForFilter
import com.progressterra.ipbandroidapi.api.iamhere.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.iamhere.models.SortData
import com.progressterra.ipbandroidapi.api.iamhere.models.TypeComparison
import com.progressterra.ipbandroidapi.api.iamhere.models.TypeVariantSort
import com.progressterra.ipbandroidview.entities.Connection
import com.progressterra.ipbandroidview.entities.toConnection
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractSource

class SuccessOutConnectionsSource(
    private val obtainAccessToken: ObtainAccessToken,
    private val service: ImhService
) : AbstractSource<Nothing, Connection>() {

    override val pageSize: Int = 6

    override suspend fun loadPage(skip: Int, take: Int): Result<List<Connection>> = runCatching {
        val token = obtainAccessToken().getOrThrow()
        service.connectsOutcoming(
            token = token,
            body = FilterAndSort(
                listFields = listOf(
                    FieldForFilter(
                        fieldName = "statusConnect",
                        listValue = listOf("success"),
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
            it.toConnection(true)
        } ?: emptyList()
    }
}