package com.progressterra.ipbandroidview.processes.usecase.user

import com.progressterra.ipbandroidview.data.UserData

interface FetchUserNameUseCase {

    suspend operator fun invoke(): String

    class Base : FetchUserNameUseCase {

        override suspend fun invoke(): String = buildString {
            if (UserData.userName.name.isNotBlank())
                append(UserData.userName.name)
            if (UserData.userName.surname.isNotBlank())
                append(" ${UserData.userName.surname}")
        }
    }
}