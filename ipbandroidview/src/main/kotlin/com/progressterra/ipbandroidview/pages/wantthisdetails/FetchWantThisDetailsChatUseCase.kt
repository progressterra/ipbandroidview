package com.progressterra.ipbandroidview.pages.wantthisdetails

import com.progressterra.ipbandroidapi.api.messenger.MessengerService
import com.progressterra.ipbandroidapi.api.messenger.models.IncomeDataForCreateDialog
import com.progressterra.ipbandroidapi.api.messenger.models.MetaDataClientWithID
import com.progressterra.ipbandroidapi.api.messenger.models.TypeDataSource
import com.progressterra.ipbandroidview.IpbAndroidViewSettings.WANT_THIS_CHAT_ID
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface FetchWantThisDetailsChatUseCase {

    suspend operator fun invoke(docId: String, docName: String): Result<String>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val manageResources: ManageResources,
        private val messengerRepository: MessengerService
    ) : AbstractTokenUseCase(obtainAccessToken), FetchWantThisDetailsChatUseCase {

        override suspend fun invoke(docId: String, docName: String): Result<String> =
            withToken { token ->
                messengerRepository.clientAreaDialog(
                    accessToken = token,
                    body = IncomeDataForCreateDialog(
                        listClients = listOf(
                            MetaDataClientWithID(
                                dataSourceType = TypeDataSource.ENTERPRISE,
                                dataSourceName = "",
                                idClient = WANT_THIS_CHAT_ID,
                                description = ""
                            ),
                            MetaDataClientWithID(
                                dataSourceType = TypeDataSource.DOCSET,
                                dataSourceName = "",
                                idClient = docId,
                                description = ""
                            ),
                        ),
                        description = "${manageResources.string(R.string.want_this_chat)} $docName",
                        additionalDataJSON = ""
                    )
                ).data?.idUnique!!
            }
    }
}