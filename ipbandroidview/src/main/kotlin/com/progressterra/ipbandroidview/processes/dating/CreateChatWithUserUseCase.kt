package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.messenger.MessengerService
import com.progressterra.ipbandroidapi.api.messenger.models.IncomeDataForCreateDialog
import com.progressterra.ipbandroidapi.api.messenger.models.MetaDataClientWithID
import com.progressterra.ipbandroidapi.api.messenger.models.TypeDataSource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface CreateChatWithUserUseCase {

    suspend operator fun invoke(user: DatingUser): Result<String>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val messengerService: MessengerService,
        private val manageResources: ManageResources, makeToastUseCase: MakeToastUseCase
    ) : CreateChatWithUserUseCase,
        AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources) {

        override suspend fun invoke(user: DatingUser): Result<String> =
            withToken { token ->
                messengerService.clientAreaDialog(
                    accessToken = token,
                    body = IncomeDataForCreateDialog(
                        listClients = listOf(
                            MetaDataClientWithID(
                                dataSourceType = TypeDataSource.CUSTOM,
                                dataSourceName = "",
                                idClient = user.id,
                                description = ""
                            )
                        ),
                        description = manageResources.string(R.string.chat),
                        additionalDataJSON = ""
                    )
                ).data?.idUnique!!
            }
    }
}