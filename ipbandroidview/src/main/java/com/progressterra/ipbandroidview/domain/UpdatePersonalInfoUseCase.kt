package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidview.base.ProvideLocation
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
        ): Result<Unit> = withToken {
            val splitName = name.split(" ")
            val firstName = splitName[0]
            val lastName = splitName[1]
            val birthday = Date.from(
                birthdayDate.atStartOfDay(
                    ZoneId.systemDefault()
                ).toInstant()
            )
            val personalResult = repo.setPersonalInfo(
                it, name = firstName, soname = lastName, dateOfBirth = birthday.format()
            )
            val emailResult = repo.setEmail(it, email)
            if (emailResult.isFailure || personalResult.isFailure) throw UseCaseException()
        }
    }
}