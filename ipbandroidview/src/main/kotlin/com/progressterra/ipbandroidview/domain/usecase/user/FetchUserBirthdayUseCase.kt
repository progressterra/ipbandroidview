package com.progressterra.ipbandroidview.domain.usecase.user

import com.progressterra.ipbandroidapi.user.UserData
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

interface FetchUserBirthdayUseCase {

    suspend fun fetch(): Result<LocalDate>

    class Base : FetchUserBirthdayUseCase {

        override suspend fun fetch(): Result<LocalDate> = runCatching {
            Instant.ofEpochMilli(UserData.dateOfBirthday).atZone(ZoneId.systemDefault())
                .toLocalDate()
        }
    }
}