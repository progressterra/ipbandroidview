package com.progressterra.ipbandroidview.processes.connection

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidapi.api.iamhere.models.FieldForFilter
import com.progressterra.ipbandroidapi.api.iamhere.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.iamhere.models.TypeComparison
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.entities.toDatingUser
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources

interface UserConnectionStatusUseCase {

    suspend operator fun invoke(clientId: String): Result<DatingUser?>

    class Base(
        private val service: ImhService,
        obtainAccessToken: ObtainAccessToken,
        makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : UserConnectionStatusUseCase,
        AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources) {

        override suspend fun invoke(clientId: String): Result<DatingUser?> = withToken { token ->
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
            ).dataList?.firstOrNull()?.toDatingUser(true) ?: service.connectsIncoming(
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
            ).dataList?.firstOrNull()?.toDatingUser(false)
        }
    }
}
