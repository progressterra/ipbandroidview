package com.progressterra.ipbandroidview.processes.usecase.user

import com.progressterra.ipbandroidview.shared.UserData

interface FetchUserEmailUseCase {

    suspend operator fun invoke(): String

    class Base : FetchUserEmailUseCase {

        override suspend fun invoke(): String = UserData.email
    }
}