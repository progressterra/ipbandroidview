package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidview.data.UserData
import com.progressterra.ipbandroidview.model.AddressUI

interface FetchUserAddressUseCase {

    suspend operator fun invoke(): Result<AddressUI>

    class Base : FetchUserAddressUseCase {

        override suspend fun invoke(): Result<AddressUI> = runCatching { UserData.address }
    }
}