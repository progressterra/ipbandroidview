package com.progressterra.ipbandroidview.pages.support

import com.progressterra.ipbandroidapi.api.messenger.MessengerRepository
import com.progressterra.ipbandroidapi.api.messenger.models.FieldForFilter
import com.progressterra.ipbandroidapi.api.messenger.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.messenger.models.SortData
import com.progressterra.ipbandroidapi.api.messenger.models.TypeComparison
import com.progressterra.ipbandroidapi.api.messenger.models.TypeVariantSort
import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.features.supportchat.SupportChatState
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractSource

class OrdersChatsSource(
    private val messengerRepository: MessengerRepository,
    private val obtainAccessToken: ObtainAccessToken
) : AbstractSource<Nothing, SupportChatState>() {

    override val pageSize = 10

    override suspend fun loadPage(skip: Int, take: Int): Result<List<SupportChatState>> =
        runCatching {
            val token = obtainAccessToken().getOrThrow()
            messengerRepository.clientAreaDialogList(
                accessToken = token,
                body = FilterAndSort(
                    listFields = listOf(
                        FieldForFilter(
                            fieldName = "idClient",
                            listValue = listOf(IpbAndroidViewSettings.ORDERS_CHAT_ID),
                            comparison = TypeComparison.EQUALS_STRONG
                        )
                    ),
                    sort = SortData(
                        fieldName = "dateUpdated",
                        variantSort = TypeVariantSort.DESC
                    ),
                    searchData = "",
                    skip = skip,
                    take = take
                )
            ).getOrThrow()?.map {
                SupportChatState(
                    id = it.idUnique!!,
                    title = it.description ?: "",
                    finite = true
                )
            } ?: emptyList()
        }
}