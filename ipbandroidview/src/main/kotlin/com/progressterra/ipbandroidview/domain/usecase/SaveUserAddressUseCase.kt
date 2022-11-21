package com.progressterra.ipbandroidview.domain.usecase

import com.progressterra.ipbandroidapi.user.UserData

interface SaveUserAddressUseCase {

    suspend fun saveAddress(address: String)

    class Base : SaveUserAddressUseCase {

        override suspend fun saveAddress(address: String) {
            UserData.address = address
        }
    }
}