package com.progressterra.ipbandroidview.processes.chat

import com.progressterra.ipbandroidapi.api.messenger.MessengerService
import com.progressterra.ipbandroidapi.api.messenger.models.FieldForFilter
import com.progressterra.ipbandroidapi.api.messenger.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.messenger.models.SortData
import com.progressterra.ipbandroidapi.api.messenger.models.TypeComparison
import com.progressterra.ipbandroidapi.api.messenger.models.TypeVariantSort
import com.progressterra.ipbandroidview.entities.DatingChat
import com.progressterra.ipbandroidview.entities.toDatingChat
import com.progressterra.ipbandroidview.entities.toMessage
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractSource

class DatingChatsSource(
    private val messengerRepository: MessengerService,
    private val obtainAccessToken: ObtainAccessToken
) : AbstractSource<Nothing, DatingChat>() {

    override val pageSize = 10

    override suspend fun loadPage(skip: Int, take: Int): Result<List<DatingChat>> =
        runCatching {
            val token = obtainAccessToken().getOrThrow()
            messengerRepository.clientAreaDialogList(
                accessToken = token,
                body = FilterAndSort(
                    listFields = listOf(),
                    sort = SortData(
                        fieldName = "dateAdded",
                        variantSort = TypeVariantSort.DESC
                    ),
                    searchData = "",
                    skip = skip,
                    take = take
                )
            ).dataList?.map {
                val lastMessage = messengerRepository.clientAreaMessageList(
                    accessToken = token,
                    body = FilterAndSort(
                        listFields = listOf(
                            FieldForFilter(
                                fieldName = "idDialog",
                                listValue = listOf(it.idUnique!!),
                                comparison = TypeComparison.EQUALS_STRONG
                            )
                        ),
                        sort = SortData(
                            fieldName = "dateAdded",
                            variantSort = TypeVariantSort.DESC
                        ),
                        searchData = "",
                        skip = 0,
                        take = 1
                    )
                ).dataList?.lastOrNull()?.toMessage()
                it.toDatingChat().copy(
                    previewMessage = lastMessage?.content ?: "",
                    lastTime = lastMessage?.date ?: ""
                )
            } ?: emptyList()
        }
}