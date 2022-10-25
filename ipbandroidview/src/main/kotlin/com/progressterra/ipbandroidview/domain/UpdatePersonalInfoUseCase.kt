package com.progressterra.ipbandroidview.domain

import android.util.Log
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.ClientGeneralData
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidview.core.AbstractUseCaseWithToken
import com.progressterra.ipbandroidview.data.ProvideLocation
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

interface UpdatePersonalInfoUseCase {

    suspend fun update(
        name: String,
        email: String,
        birthdayDate: LocalDate
    ): Result<ClientGeneralData>

    suspend fun update(name: String, email: String): Result<ClientGeneralData>

    class Base(
        private val repo: SCRMRepository, provideLocation: ProvideLocation
    ) : UpdatePersonalInfoUseCase, AbstractUseCaseWithToken(repo, provideLocation) {

        override suspend fun update(name: String, email: String): Result<ClientGeneralData> =
            runCatching {
                Log.d("NAME", name)
                val splitName = name.trim().split(" ")
                val firstName = splitName[0]
                val lastName = splitName[1]
                Log.d("NAME", "first: $firstName, last: $lastName")
                withToken { repo.setEmail(it, email) }.onFailure { throw it }
                withToken {
                    repo.setPersonalInfo(
                        it,
                        name = firstName,
                        soname = lastName,
                        sex = null,
                        patronymic = null,
                        dateOfBirth = null,
                        comment = null
                    )
                }.getOrThrow()
            }

        override suspend fun update(
            name: String, email: String, birthdayDate: LocalDate
        ): Result<ClientGeneralData> = runCatching {
            update(name, email).onFailure { throw it }
            val birthday = Date.from(
                birthdayDate.atStartOfDay(
                    ZoneId.systemDefault()
                ).toInstant()
            )
            withToken {
                repo.setPersonalInfo(
                    it,
                    dateOfBirth = birthday.format(),
                    sex = null,
                    soname = null,
                    name = null,
                    patronymic = null,
                    comment = null
                )
            }.getOrThrow()
        }
    }
}