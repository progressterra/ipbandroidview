package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidview.shared.UserData

interface FetchUserPhoneUseCase {

    suspend operator fun invoke(): String

    class Base : FetchUserPhoneUseCase {

        override suspend fun invoke(): String = UserData.phone
    }
}