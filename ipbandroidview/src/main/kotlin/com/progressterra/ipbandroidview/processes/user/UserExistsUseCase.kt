package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidview.shared.UserData

interface UserExistsUseCase {

    suspend operator fun invoke(): Boolean

    class Base : UserExistsUseCase {

        override suspend fun invoke(): Boolean = UserData.clientExist
    }
}