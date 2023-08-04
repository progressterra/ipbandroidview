package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.ClientDataIncome
import com.progressterra.ipbandroidapi.api.scrm.model.IncomeDataEmail
import com.progressterra.ipbandroidview.entities.formatZdtIso
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.UserName
import com.progressterra.ipbandroidview.shared.splitName
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState
import java.time.ZoneId
import java.time.ZonedDateTime


interface SaveDataUseCase {

    suspend operator fun invoke(income: EditUserState): Result<Unit>

    class Base(
        private val scrmRepository: SCRMRepository, obtainAccessToken: ObtainAccessToken
    ) : SaveDataUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(income: EditUserState): Result<Unit> = withToken { token ->
            val nameList = income.name.formatByType().splitName(false)
            UserData.userName = UserName(
                name = nameList[0], surname = nameList[1]
            )
            val birthday = income.birthday.formatByType()
            val zonedDateTimeBirthday = ZonedDateTime.of(
                birthday.subSequence(6, 10).toString().toInt(),
                birthday.subSequence(3, 5).toString().toInt(),
                birthday.subSequence(0, 2).toString().toInt(),
                0,
                0,
                0,
                0,
                ZoneId.systemDefault()
            )
            UserData.dateOfBirthday = zonedDateTimeBirthday.formatZdtIso()
            UserData.phone = income.phone.formatByType()
            UserData.email = income.email.formatByType()
            scrmRepository.setPersonalInfo(
                accessToken = token, request = ClientDataIncome(
                    name = nameList[0],
                    soname = nameList[1],
                    dateOfBirth = UserData.dateOfBirthday
                )
            )
            scrmRepository.setEmail(
                accessToken = token, request = IncomeDataEmail(
                    email = income.email.formatByType()
                )
            )
        }
    }
}