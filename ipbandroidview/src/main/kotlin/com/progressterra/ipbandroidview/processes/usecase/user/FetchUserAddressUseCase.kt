package com.progressterra.ipbandroidview.processes.usecase.user

import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.entities.AddressUI

interface FetchUserAddressUseCase {

    suspend operator fun invoke(): Result<AddressUI>

    class Base : FetchUserAddressUseCase {

        override suspend fun invoke(): Result<AddressUI> = runCatching { UserData.address }
    }
}