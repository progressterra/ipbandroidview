package com.progressterra.ipbandroidview.processes.chat

import com.progressterra.ipbandroidapi.api.messenger.MessengerService
import com.progressterra.ipbandroidapi.api.messenger.models.IncomeDataForCreateDialog
import com.progressterra.ipbandroidapi.api.messenger.models.MetaDataClientWithID
import com.progressterra.ipbandroidapi.api.messenger.models.TypeDataSource
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase

interface CreateChatCustomUseCase {

    suspend operator fun invoke(id: String): Result<String>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val messengerService: MessengerService,
        manageResources: ManageResources,
        makeToastUseCase: MakeToastUseCase
    ) : CreateChatCustomUseCase,
        AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources) {

        override suspend fun invoke(id: String): Result<String> =
            withToken { token ->
                messengerService.clientAreaDialog(
                    accessToken = token,
                    body = IncomeDataForCreateDialog(
                        listClients = listOf(
                            MetaDataClientWithID(
                                dataSourceType = TypeDataSource.CUSTOM,
                                dataSourceName = "",
                                idClient = id,
                                description = ""
                            )
                        ),
                        description = "",
                        additionalDataJSON = ""
                    )
                ).data?.idUnique!!
            }
    }
}