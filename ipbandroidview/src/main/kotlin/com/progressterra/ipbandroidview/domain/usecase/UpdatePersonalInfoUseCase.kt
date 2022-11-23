package com.progressterra.ipbandroidview.domain.usecase

import com.progressterra.ipbandroidapi.Constants
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.ClientDataIncome
import com.progressterra.ipbandroidapi.api.scrm.model.IncomeDataEmail
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidapi.user.UserName
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.core.SplitName
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

interface UpdatePersonalInfoUseCase {

    suspend fun update(
        name: String, email: String, birthdayDate: LocalDate
    ): Result<Unit>

    suspend fun update(name: String, email: String): Result<Unit>

    class Base(
        private val splitName: SplitName,
        private val repo: SCRMRepository,
        provideLocation: ProvideLocation
    ) : UpdatePersonalInfoUseCase, AbstractUseCase(repo, provideLocation) {

        override suspend fun update(
            name: String, email: String
        ): Result<Unit> = withToken { token ->
            val nameList = splitName.splitName(name, false)
            repo.setEmail(token, IncomeDataEmail(email)).onFailure { throw it }
            repo.setPersonalInfo(
                token, ClientDataIncome(
                    sex = 0,
                    name = nameList[0],
                    soname = nameList[1],
                    patronymic = "",
                    dateOfBirth = Constants.EMPTY_DATE,
                    comment = ""
                )
            ).onFailure { throw it }
            UserData.userName = UserName(
                name = nameList[0],
                surname = nameList[1]
            )
            UserData.email = email
        }

        override suspend fun update(
            name: String, email: String, birthdayDate: LocalDate
        ): Result<Unit> = withToken { token ->
            val nameList = splitName.splitName(name, false)
            val birthday = Date.from(
                birthdayDate.atStartOfDay(
                    ZoneId.systemDefault()
                ).toInstant()
            )
            repo.setEmail(token, IncomeDataEmail(email)).onFailure { throw it }
            repo.setPersonalInfo(
                token, ClientDataIncome(
                    sex = 0,
                    name = nameList[0],
                    soname = nameList[1],
                    patronymic = "",
                    dateOfBirth = birthday.format(),
                    comment = ""
                )
            ).onFailure { throw it }
            UserData.userName = UserName(
                name = nameList[0],
                surname = nameList[1]
            )
            UserData.email = email
        }
    }
}