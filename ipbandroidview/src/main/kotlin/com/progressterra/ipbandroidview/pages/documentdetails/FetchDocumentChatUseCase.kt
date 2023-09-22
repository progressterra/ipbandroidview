package com.progressterra.ipbandroidview.pages.documentdetails

import com.progressterra.ipbandroidapi.api.messenger.MessengerService
import com.progressterra.ipbandroidapi.api.messenger.models.IncomeDataForCreateDialog
import com.progressterra.ipbandroidapi.api.messenger.models.MetaDataClientWithID
import com.progressterra.ipbandroidapi.api.messenger.models.TypeDataSource
import com.progressterra.ipbandroidview.IpbAndroidViewSettings.DOCS_CHAT_ID
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface FetchDocumentChatUseCase {

    suspend operator fun invoke(docId: String, docName: String): Result<String>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val manageResources: ManageResources,
        private val messengerRepository: MessengerService
    ) : AbstractTokenUseCase(obtainAccessToken), FetchDocumentChatUseCase {

        override suspend fun invoke(docId: String, docName: String): Result<String> =
            withToken { token ->
                messengerRepository.clientAreaDialog(
                    accessToken = token,
                    body = IncomeDataForCreateDialog(
                        listClients = listOf(
                            MetaDataClientWithID(
                                dataSourceType = TypeDataSource.ENTERPRISE,
                                dataSourceName = "",
                                idClient = DOCS_CHAT_ID,
                                description = ""
                            ),
                            MetaDataClientWithID(
                                dataSourceType = TypeDataSource.DOCSET,
                                dataSourceName = "",
                                idClient = docId,
                                description = ""
                            ),
                        ),
                        description = "${manageResources.string(R.string.docs_chat)} $docName",
                        additionalDataJSON = ""
                    )
                ).data?.idUnique!!
            }
    }
}