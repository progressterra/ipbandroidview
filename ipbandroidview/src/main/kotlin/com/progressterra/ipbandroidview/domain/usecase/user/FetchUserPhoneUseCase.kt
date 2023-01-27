package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidview.data.UserData

interface FetchUserPhoneUseCase {

    suspend operator fun invoke(): String

    class Base : FetchUserPhoneUseCase {

        override suspend fun invoke(): String = UserData.phone
    }
}