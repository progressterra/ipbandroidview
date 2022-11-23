package com.progressterra.ipbandroidview.domain.usecase

import com.progressterra.ipbandroidapi.user.UserData
import java.time.LocalDate

interface FetchUserBirthdayUseCase {

    suspend fun fetch(): Result<LocalDate>

    class Base : FetchUserBirthdayUseCase {

        override suspend fun fetch(): Result<LocalDate> = runCatching {
            LocalDate.ofEpochDay(UserData.dateOfBirthday)
        }
    }
}