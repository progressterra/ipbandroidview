package com.progressterra.ipbandroidview.processes.dating

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidview.entities.Connection
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.PagingUseCase

interface SuccessOutConnectionsUseCase : PagingUseCase<Nothing, Connection> {

    class Base(
        private val obtainAccessToken: ObtainAccessToken,
        private val service: ImhService
    ) : SuccessOutConnectionsUseCase, PagingUseCase.Abstract<Nothing, Connection>() {

        override fun createSource() = SuccessOutConnectionsSource(obtainAccessToken, service)
    }
}