package com.progressterra.ipbandroidview.pages.support

import com.progressterra.ipbandroidapi.api.messenger.MessengerService
import com.progressterra.ipbandroidapi.api.messenger.models.FieldForFilter
import com.progressterra.ipbandroidapi.api.messenger.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.messenger.models.SortData
import com.progressterra.ipbandroidapi.api.messenger.models.TypeComparison
import com.progressterra.ipbandroidapi.api.messenger.models.TypeVariantSort
import com.progressterra.ipbandroidview.entities.Message
import com.progressterra.ipbandroidview.entities.toMessage
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractSource

class MessageSource(
    private val messengerRepository: MessengerService,
    private val obtainAccessToken: ObtainAccessToken
) : AbstractSource<String, Message>() {

    override val pageSize: Int = 10

    override suspend fun loadPage(skip: Int, take: Int): Result<List<Message>> = runCatching {
        val token = obtainAccessToken().getOrThrow()
        messengerRepository.clientAreaMessageList(
            accessToken = token,
            body = FilterAndSort(
                listFields = listOf(
                    FieldForFilter(
                        fieldName = "idDialog",
                        listValue = listOf(filter!!),
                        comparison = TypeComparison.EQUALS_STRONG
                    )
                ),
                sort = SortData(
                    fieldName = "dateAdded",
                    variantSort = TypeVariantSort.DESC
                ),
                searchData = "",
                skip = skip,
                take = take
            )
        ).dataList?.map { it.toMessage() } ?: emptyList()
    }
}