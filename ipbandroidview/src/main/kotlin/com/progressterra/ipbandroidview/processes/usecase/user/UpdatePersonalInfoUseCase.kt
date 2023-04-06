package com.progressterra.ipbandroidview.processes.usecase.user

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.ClientDataIncome
import com.progressterra.ipbandroidapi.api.scrm.model.IncomeDataEmail
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.ProvideLocation
import com.progressterra.ipbandroidview.shared.SplitName
import com.progressterra.ipbandroidview.shared.Constants
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.UserName
import com.progressterra.ipbandroidview.ext.throwOnFailure
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

interface UpdatePersonalInfoUseCase {

    suspend operator fun invoke(
        name: String, email: String, birthdayDate: LocalDate
    ): Result<Unit>

    suspend operator fun invoke(name: String, email: String): Result<Unit>

    class Base(
        private val splitName: SplitName,
        private val repo: SCRMRepository,
        provideLocation: ProvideLocation
    ) : UpdatePersonalInfoUseCase, AbstractUseCase(repo, provideLocation) {

        override suspend fun invoke(
            name: String, email: String
        ): Result<Unit> = withToken { token ->
            val nameList = splitName.splitName(name, false)
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
            val nameList = splitName.splitName(name, false)
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