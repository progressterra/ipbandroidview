package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.model.ClientDataIncome
import com.progressterra.ipbandroidapi.api.scrm.model.IncomeDataEmail
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.UserName
import com.progressterra.ipbandroidview.shared.splitName
import com.progressterra.ipbandroidview.shared.toEpochMillis
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState
import java.util.Date

interface SaveDataUseCase {

    suspend operator fun invoke(income: EditUserState): Result<Unit>

    class Base(
        private val scrmRepository: SCRMRepository, provideLocation: ProvideLocation
    ) : SaveDataUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(income: EditUserState): Result<Unit> = withToken { token ->
            val nameList = income.name.text.splitName(false)
            UserData.userName = UserName(
                name = nameList[0], surname = nameList[1]
            )
            UserData.dateOfBirthday = income.birthday.text.toEpochMillis()
            UserData.phone = income.phone.text
            UserData.email = income.email.text
            scrmRepository.setPersonalInfo(
                accessToken = token, request = ClientDataIncome(
                    name = nameList[0],
                    soname = nameList[1],
                    dateOfBirth = Date(income.birthday.text.toEpochMillis()).format()
                )
            )
            scrmRepository.setEmail(
                accessToken = token, request = IncomeDataEmail(
                    email = income.email.text
                )
            )
        }
    }
}