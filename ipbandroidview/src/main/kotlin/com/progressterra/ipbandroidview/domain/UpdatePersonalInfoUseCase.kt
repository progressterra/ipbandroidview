package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidview.core.AbstractUseCaseWithToken
import com.progressterra.ipbandroidview.data.ProvideLocation
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

interface UpdatePersonalInfoUseCase {

    suspend fun update(name: String, email: String, birthdayDate: LocalDate): Result<Unit>

    class Base(
        private val repo: SCRMRepository, provideLocation: ProvideLocation
    ) : UpdatePersonalInfoUseCase, AbstractUseCaseWithToken(repo, provideLocation) {

        override suspend fun update(
            name: String, email: String, birthdayDate: LocalDate
        ): Result<Unit> = handle {
            val splitName = name.split(" ")
            val firstName = splitName[0]
            val lastName = splitName[1]
            val birthday = Date.from(
                birthdayDate.atStartOfDay(
                    ZoneId.systemDefault()
                ).toInstant()
            )
            withToken {
                repo.setPersonalInfo(
                    it, name = firstName, soname = lastName, dateOfBirth = birthday.format()
                )
            }.onFailure { throw it }
            withToken { repo.setEmail(it, email) }.onFailure { throw it }
        }
    }
}