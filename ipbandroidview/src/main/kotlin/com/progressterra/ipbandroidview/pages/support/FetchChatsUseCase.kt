package com.progressterra.ipbandroidview.pages.support

import androidx.paging.PagingData
import com.progressterra.ipbandroidapi.api.messenger.MessengerRepository
import com.progressterra.ipbandroidapi.api.messenger.models.FieldForFilter
import com.progressterra.ipbandroidapi.api.messenger.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.messenger.models.IncomeDataForCreateDialog
import com.progressterra.ipbandroidapi.api.messenger.models.SortData
import com.progressterra.ipbandroidapi.api.messenger.models.TypeComparison
import com.progressterra.ipbandroidapi.api.messenger.models.TypeVariantSort
import com.progressterra.ipbandroidview.IpbAndroidViewSettings.DEFAULT_ID
import com.progressterra.ipbandroidview.IpbAndroidViewSettings.DOCS_CHAT_ID
import com.progressterra.ipbandroidview.IpbAndroidViewSettings.MAIN_CHAT_ID
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
        private val ordersChatsUseCase: OrdersChatsUseCase
    ) : FetchChatsUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<SupportChatState> = withToken { token ->
            val docsDialog = messengerRepository.clientAreaDialog(
                accessToken = token,
                body = IncomeDataForCreateDialog(
                    listIDClients = listOf(
                        DOCS_CHAT_ID,
                        DEFAULT_ID
                    ),
                    description = manageResources.string(R.string.documents),
                    additionalDataJSON = ""
                )
            ).getOrThrow()
            val mainDialog = messengerRepository.clientAreaDialog(
                accessToken = token,
                body = IncomeDataForCreateDialog(
                    listIDClients = listOf(
                        MAIN_CHAT_ID,
                        DEFAULT_ID
                    ),
                    description = manageResources.string(R.string.support),
                    additionalDataJSON = ""
                )
            ).getOrThrow()
            val wantThisDialog = messengerRepository.clientAreaDialog(
                accessToken = token,
                body = IncomeDataForCreateDialog(
                    listIDClients = listOf(
                        WANT_THIS_CHAT_ID,
                        DEFAULT_ID
                    ),
                    description = manageResources.string(R.string.want_this),
                    additionalDataJSON = ""
                )
            ).getOrThrow()
            val docsLastMessage = messengerRepository.messageList(
                accessToken = token,
                body = FilterAndSort(
                    listFields = listOf(
                        FieldForFilter(
                            fieldName = "idDialog",
                            listValue = listOf(docsDialog?.idUnique!!),
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
            val wantThisLastMessage = messengerRepository.messageList(
                accessToken = token,
                body = FilterAndSort(
                    listFields = listOf(
                        FieldForFilter(
                            fieldName = "idDialog",
                            listValue = listOf(wantThisDialog?.idUnique!!),
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
            val mainLastMessage = messengerRepository.messageList(
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
            val docs = SupportChatState(
                id = docsDialog.idUnique!!,
                title = docsDialog.description ?: "",
                iconRes = R.drawable.ic_chat_docs,
                finite = true,
                lastMessage = docsLastMessage?.content ?: "",
                lastTime = docsLastMessage?.date ?: ""
            )
            val main = SupportChatState(
                id = mainDialog.idUnique!!,
                title = mainDialog.description ?: "",
                iconRes = R.drawable.ic_chat_common,
                finite = true,
                lastMessage = mainLastMessage?.content ?: "",
                lastTime = mainLastMessage?.date ?: ""
            )
            val wantThis = SupportChatState(
                id = wantThisDialog.idUnique!!,
                title = wantThisDialog.description ?: "",
                iconRes = R.drawable.ic_chat_want_this,
                finite = true,
                lastMessage = wantThisLastMessage?.content ?: "",
                lastTime = wantThisLastMessage?.date ?: ""
            )
            val orders = SupportChatState(
                id = "",
                title = manageResources.string(R.string.your_orders),
                subCategories = ordersChatsUseCase().getOrThrow(),
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
