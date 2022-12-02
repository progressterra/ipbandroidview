package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidview.data.UserData

interface FetchUserNameUseCase {

    suspend operator fun invoke(): Result<String>

    class Base : FetchUserNameUseCase {

        override suspend fun invoke(): Result<String> = runCatching {
            buildString {
                if (UserData.userName.name.isNotBlank())
                    append("${UserData.userName.name} ")
                if (UserData.userName.surname.isNotBlank())
                    append(UserData.userName.surname)
            }.trim()
        }
    }
}