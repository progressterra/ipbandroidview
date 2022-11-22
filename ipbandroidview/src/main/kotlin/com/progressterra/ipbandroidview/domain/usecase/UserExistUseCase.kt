package com.progressterra.ipbandroidview.domain.usecase

import com.progressterra.ipbandroidapi.user.UserData

interface UserExistUseCase {

    suspend fun userExist(): Boolean

    class Base : UserExistUseCase {

        override suspend fun userExist(): Boolean = UserData.clientExist

    }
}