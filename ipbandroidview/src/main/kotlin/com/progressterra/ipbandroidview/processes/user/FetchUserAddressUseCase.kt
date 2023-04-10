package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.shared.UserData

interface FetchUserAddressUseCase {

    suspend operator fun invoke(): Result<AddressUI>

    class Base : FetchUserAddressUseCase {

        override suspend fun invoke(): Result<AddressUI> = runCatching { UserData.address }
    }
}