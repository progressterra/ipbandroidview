package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidview.data.UserData

interface UserExistsUseCase {

    suspend operator fun invoke(): Boolean

    class Base : UserExistsUseCase {

        override suspend fun invoke(): Boolean = UserData.clientExist
    }
}