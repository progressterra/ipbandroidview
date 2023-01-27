package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidview.data.UserData

interface FetchUserEmailUseCase {

    suspend operator fun invoke(): String

    class Base : FetchUserEmailUseCase {

        override suspend fun invoke(): String = UserData.email
    }
}