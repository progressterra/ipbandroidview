package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidview.features.authprofile.AuthProfileState
import com.progressterra.ipbandroidview.shared.UserData

interface FetchUserProfileUseCase {

    suspend operator fun invoke(): Result<AuthProfileState>

    class Base : FetchUserProfileUseCase {

        override suspend fun invoke(): Result<AuthProfileState> = runCatching {
            AuthProfileState(
                profileImage = "https://placekitten.com/200/200",
                name = buildString {
                    if (UserData.userName.name.isNotBlank()) append(UserData.userName.name)
                    if (UserData.userName.surname.isNotBlank()) append(" ${UserData.userName.surname}")
                },
                email = UserData.email
            )
        }
    }
}