package com.progressterra.ipbandroidview.processes.interests

import com.progressterra.ipbandroidapi.api.iamhere.ImhService
import com.progressterra.ipbandroidview.entities.Interest
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.PagingUseCase

interface FetchInterestsUseCase : PagingUseCase<Nothing, Interest> {

    class Base(
        private val service: ImhService,
        private val obtainAccessToken: ObtainAccessToken
    ) : FetchInterestsUseCase, PagingUseCase.Abstract<Nothing, Interest>() {

        override fun createSource() = InterestsSource(service, obtainAccessToken)
    }
}
