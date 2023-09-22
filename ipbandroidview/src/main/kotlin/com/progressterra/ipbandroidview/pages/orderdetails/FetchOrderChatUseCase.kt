package com.progressterra.ipbandroidview.pages.orderdetails

import com.progressterra.ipbandroidapi.api.messenger.MessengerService
import com.progressterra.ipbandroidapi.api.messenger.models.IncomeDataForCreateDialog
import com.progressterra.ipbandroidapi.api.messenger.models.MetaDataClientWithID
import com.progressterra.ipbandroidapi.api.messenger.models.TypeDataSource
import com.progressterra.ipbandroidview.IpbAndroidViewSettings.ORDERS_CHAT_ID
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface FetchOrderChatUseCase {

    suspend operator fun invoke(orderId: String, orderNumber: String): Result<String>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val manageResources: ManageResources,
        private val messengerRepository: MessengerService
    ) : AbstractTokenUseCase(obtainAccessToken), FetchOrderChatUseCase {

        override suspend fun invoke(orderId: String, orderNumber: String): Result<String> =
            withToken { token ->
                messengerRepository.clientAreaDialog(
                    accessToken = token,
                    body = IncomeDataForCreateDialog(
                        listClients = listOf(
                            MetaDataClientWithID(
                                dataSourceType = TypeDataSource.ENTERPRISE,
                                dataSourceName = "",
                                idClient = ORDERS_CHAT_ID,
                                description = ""
                            ),
                            MetaDataClientWithID(
                                dataSourceType = TypeDataSource.ORDER,
                                dataSourceName = "",
                                idClient = orderId,
                                description = ""
                            ),
                        ),
                        description = "${manageResources.string(R.string.order_chat)} $orderNumber",
                        additionalDataJSON = ""
                    )
                ).data?.idUnique!!
            }
    }
}