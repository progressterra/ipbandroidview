package com.progressterra.ipbandroidview.pages.support

import androidx.paging.PagingData
import com.progressterra.ipbandroidapi.api.messenger.MessengerRepository
import com.progressterra.ipbandroidapi.api.messenger.models.FieldForFilter
import com.progressterra.ipbandroidapi.api.messenger.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.messenger.models.IncomeDataForCreateDialog
import com.progressterra.ipbandroidapi.api.messenger.models.MetaDataClientWithID
import com.progressterra.ipbandroidapi.api.messenger.models.SortData
import com.progressterra.ipbandroidapi.api.messenger.models.TypeComparison
import com.progressterra.ipbandroidapi.api.messenger.models.TypeDataSource
import com.progressterra.ipbandroidapi.api.messenger.models.TypeVariantSort
import com.progressterra.ipbandroidview.IpbAndroidViewSettings.DOCS_CHAT_ID
import com.progressterra.ipbandroidview.IpbAndroidViewSettings.MAIN_CHAT_ID
import com.progressterra.ipbandroidview.IpbAndroidViewSettings.ORDERS_CHAT_ID
import com.progressterra.ipbandroidview.IpbAndroidViewSettings.WANT_THIS_CHAT_ID
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.toMessage
import com.progressterra.ipbandroidview.features.supportchat.SupportChatState
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources
import kotlinx.coroutines.flow.flowOf

interface FetchChatsUseCase {

    suspend operator fun invoke(): Result<SupportChatState>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val manageResources: ManageResources,
        private val messengerRepository: MessengerRepository,
        private val chatsUseCase: ChatsUseCase
    ) : FetchChatsUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<SupportChatState> = withToken { token ->
            val mainDialog = messengerRepository.clientAreaDialog(
                accessToken = token,
                body = IncomeDataForCreateDialog(
                    listClients = listOf(
                        MetaDataClientWithID(
                            dataSourceType = TypeDataSource.ENTERPRISE,
                            dataSourceName = "",
                            description = "",
                            idClient = MAIN_CHAT_ID
                        )
                    ),
                    description = manageResources.string(R.string.support),
                    additionalDataJSON = ""
                )
            ).getOrThrow()
            val mainLastMessage = messengerRepository.clientAreaMessageList(
                accessToken = token,
                body = FilterAndSort(
                    listFields = listOf(
                        FieldForFilter(
                            fieldName = "idDialog",
                            listValue = listOf(mainDialog?.idUnique!!),
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
            ).getOrThrow()?.lastOrNull()?.toMessage()
            val main = SupportChatState(
                id = mainDialog.idUnique!!,
                title = mainDialog.description ?: "",
                iconRes = R.drawable.ic_chat_common,
                finite = true,
                lastMessage = mainLastMessage?.content ?: "",
                lastTime = mainLastMessage?.date ?: ""
            )
            val docs = SupportChatState(
                id = "0",
                title = manageResources.string(R.string.docs_chats),
                iconRes = R.drawable.ic_chat_docs,
                subCategories = chatsUseCase(DOCS_CHAT_ID).getOrThrow()
            )
            val wantThis = SupportChatState(
                id = "1",
                title = manageResources.string(R.string.want_this_requests),
                subCategories = chatsUseCase(WANT_THIS_CHAT_ID).getOrThrow(),
                iconRes = R.drawable.ic_chat_want_this
            )
            val orders = SupportChatState(
                id = "2",
                title = manageResources.string(R.string.your_orders),
                subCategories = chatsUseCase(ORDERS_CHAT_ID).getOrThrow(),
                iconRes = R.drawable.ic_chat_orders
            )
            SupportChatState(
                title = manageResources.string(R.string.support),
                subCategories = flowOf(
                    PagingData.from(
                        listOf(
                            main,
                            wantThis,
                            docs,
                            orders
                        )
                    )
                )
            )
        }
    }
}
