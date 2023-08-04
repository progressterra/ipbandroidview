package com.progressterra.ipbandroidview.pages.orderdetails

import com.progressterra.ipbandroidapi.api.messenger.MessengerRepository
import com.progressterra.ipbandroidapi.api.messenger.models.IncomeDataForCreateDialog
import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.IpbAndroidViewSettings.DEFAULT_ID
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface FetchOrderChatUseCase {

    suspend operator fun invoke(orderId: String, orderNumber: String): Result<String>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val manageResources: ManageResources,
        private val messengerRepository: MessengerRepository
    ) : AbstractTokenUseCase(obtainAccessToken), FetchOrderChatUseCase {

        override suspend fun invoke(orderId: String, orderNumber: String): Result<String> = withToken { token ->
            messengerRepository.clientAreaDialog(
                accessToken = token,
                body = IncomeDataForCreateDialog(
                    listIDClients = listOf(
                        IpbAndroidViewSettings.ORDERS_CHAT_ID,
                        orderId,
                        DEFAULT_ID
                    ),
                    description = "${manageResources.string(R.string.order_chat)} $orderNumber",
                    additionalDataJSON = ""
                )
            ).getOrThrow()?.idUnique!!
        }
    }
}