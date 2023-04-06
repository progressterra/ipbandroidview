package com.progressterra.ipbandroidview.processes.usecase.user

import com.progressterra.ipbandroidview.shared.UserData
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

interface FetchUserBirthdayUseCase {

    suspend operator fun invoke(): Result<LocalDate>

    class Base : FetchUserBirthdayUseCase {

        override suspend fun invoke(): Result<LocalDate> = runCatching {
            Instant.ofEpochMilli(UserData.dateOfBirthday).atZone(ZoneId.systemDefault())
                .toLocalDate()
        }
    }
}