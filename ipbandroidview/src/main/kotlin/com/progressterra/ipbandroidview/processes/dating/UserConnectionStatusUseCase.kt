package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.FieldForFilter
import com.progressterra.ipbandroidapi.api.iamhere.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.iamhere.models.TypeComparison
import com.progressterra.ipbandroidview.entities.Connection
import com.progressterra.ipbandroidview.entities.toConnection
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface UserConnectionStatusUseCase {

    suspend operator fun invoke(clientId: String): Result<Connection?>

    class Base(
        private val service: ImhService,
        obtainAccessToken: ObtainAccessToken,
        makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : UserConnectionStatusUseCase,
        AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources) {

        override suspend fun invoke(clientId: String): Result<Connection?> = withToken { token ->
            service.connectsOutcoming(
                token = token,
                body = FilterAndSort(
                    listFields = listOf(
                        FieldForFilter(
                            fieldName = "idClientTarget",
                            listValue = listOf(clientId),
                            comparison = TypeComparison.EQUALS_STRONG
                        )
                    ), sort = null,
                    searchData = "",
                    skip = 0,
                    take = 1
                )
            ).dataList?.firstOrNull()?.toConnection(true) ?: service.connectsIncoming(
                token = token,
                body = FilterAndSort(
                    listFields = listOf(
                        FieldForFilter(
                            fieldName = "idClientInitiator",
                            listValue = listOf(clientId),
                            comparison = TypeComparison.EQUALS_STRONG
                        )
                    ), sort = null,
                    searchData = "",
                    skip = 0,
                    take = 1
                )
            ).dataList?.firstOrNull()?.toConnection(false)
        }
    }
}
