package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.ClientDataIncome
import com.progressterra.ipbandroidapi.api.scrm.model.IncomeDataEmail
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.Constants
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.UserName
import com.progressterra.ipbandroidview.shared.string.splitName
import com.progressterra.ipbandroidview.shared.throwOnFailure
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

interface UpdatePersonalInfoUseCase {

    suspend operator fun invoke(
        name: String, email: String, birthdayDate: LocalDate
    ): Result<Unit>

    suspend operator fun invoke(name: String, email: String): Result<Unit>

    class Base(
        private val repo: SCRMRepository,
        provideLocation: ProvideLocation
    ) : UpdatePersonalInfoUseCase, AbstractUseCase(repo, provideLocation) {

        override suspend fun invoke(
            name: String, email: String
        ): Result<Unit> = withToken { token ->
            val nameList = name.splitName(false)
            repo.setEmail(token, IncomeDataEmail(email)).throwOnFailure()
            repo.setPersonalInfo(
                token, ClientDataIncome(
                    sex = 0,
                    name = nameList[0],
                    soname = nameList[1],
                    patronymic = "",
                    dateOfBirth = Constants.DEFAULT_DATE,
                    comment = ""
                )
            ).throwOnFailure()
            UserData.userName = UserName(
                name = nameList[0],
                surname = nameList[1]
            )
            UserData.email = email
        }

        override suspend fun invoke(
            name: String, email: String, birthdayDate: LocalDate
        ): Result<Unit> = withToken { token ->
            val nameList = name.splitName(false)
            val birthday = Date.from(
                birthdayDate.atStartOfDay(
                    ZoneId.systemDefault()
                ).toInstant()
            )
            repo.setEmail(token, IncomeDataEmail(email)).throwOnFailure()
            repo.setPersonalInfo(
                token, ClientDataIncome(
                    sex = 0,
                    name = nameList[0],
                    soname = nameList[1],
                    patronymic = "",
                    dateOfBirth = birthday.format(),
                    comment = ""
                )
            ).throwOnFailure()
            UserData.userName = UserName(
                name = nameList[0],
                surname = nameList[1]
            )
            UserData.email = email
        }
    }
}