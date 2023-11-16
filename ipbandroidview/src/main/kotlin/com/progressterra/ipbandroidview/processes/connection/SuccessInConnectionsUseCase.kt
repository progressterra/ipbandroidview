package com.progressterra.ipbandroidview.processes.connection

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.PagingUseCase

interface SuccessInConnectionsUseCase : PagingUseCase<Nothing, DatingUser> {

    class Base(
        private val obtainAccessToken: ObtainAccessToken,
        private val service: ImhService
    ) : SuccessInConnectionsUseCase, PagingUseCase.Abstract<Nothing, DatingUser>() {

        override fun createSource() = SuccessInConnectionsSource(obtainAccessToken, service)
    }
}