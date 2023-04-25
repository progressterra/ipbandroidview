package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.UserName
import com.progressterra.ipbandroidview.shared.splitName
import com.progressterra.ipbandroidview.shared.toAddress
import com.progressterra.ipbandroidview.shared.toEpochMillis
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState

interface SaveDataUseCase {

    suspend operator fun invoke(income: EditUserState): Result<Unit>

    class Base(
        private val scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation
    ) : SaveDataUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        //TODO saving
        override suspend fun invoke(income: EditUserState): Result<Unit> = runCatching {
            val nameList = income.name.text.splitName(false)
            UserData.userName = UserName(
                name = nameList[0],
                surname = nameList[1]
            )
            UserData.dateOfBirthday = income.birthday.text.toEpochMillis()
            UserData.phone = income.phone.text
            UserData.email = income.email.text
            UserData.citizenship = income.citizenship.text
            val address = income.address.text.toAddress()
            UserData.address = AddressUI(
                nameCity = address.city,
                nameStreet = address.street,
                houseNUmber = address.building,
                apartment = address.apartment?.toInt() ?: 0
            )
            UserData.passport = income.passport.text
            UserData.passportProvider = income.passportProvider.text
            UserData.passportProviderCode = income.passportProviderCode.text
            UserData.patent = income.patent.text
        }
    }
}