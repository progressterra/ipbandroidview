package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidapi.api.scrm.ScrmService
import com.progressterra.ipbandroidapi.api.scrm.models.ClientsEntity
import com.progressterra.ipbandroidapi.api.scrm.models.IncomeDataChannel
import com.progressterra.ipbandroidapi.api.scrm.models.TypeSex
import com.progressterra.ipbandroidview.entities.Sex
import com.progressterra.ipbandroidview.entities.formatZdtIso
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.log
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState
import java.time.ZoneOffset
import java.time.ZonedDateTime

interface SaveDataUseCase {

    suspend operator fun invoke(income: EditUserState): Result<Unit>

    class Base(
        private val scrmService: ScrmService,
        obtainAccessToken: ObtainAccessToken, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : SaveDataUseCase,
        AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources) {

        override suspend fun invoke(income: EditUserState): Result<Unit> = withToken { token ->
            IpbAndroidViewSettings.AVAILABLE_PROFILE_FIELDS.forEach {
                when (it) {
                    "name" -> {
                        UserData.userName =
                            UserData.userName.copy(name = income.name.formatByType())
                    }

                    "soname" -> {
                        UserData.userName =
                            UserData.userName.copy(soname = income.soname.formatByType())
                    }

                    "patronymic" -> {
                        UserData.userName =
                            UserData.userName.copy(patronymic = income.patronymic.formatByType())
                    }

                    "eMailGeneral" -> {
                        UserData.email = income.email.formatByType()
                    }

                    "sex" -> {
                        UserData.sex = when (income.sex) {
                            Sex.MALE -> 1
                            Sex.FEMALE -> 2
                            null -> 0
                        }
                    }

                    "dateOfBirth" -> {
                        val birthday = income.birthday.formatByType()
                        val zonedDateTimeBirthday = ZonedDateTime.of(
                            birthday.subSequence(6, 10).toString().toInt(),
                            birthday.subSequence(3, 5).toString().toInt(),
                            birthday.subSequence(0, 2).toString().toInt(),
                            0,
                            0,
                            0,
                            0,
                            ZoneOffset.UTC
                        )
                        UserData.dateOfBirthday = zonedDateTimeBirthday.formatZdtIso()
                    }
                }
            }
            log("USER", "DATA: $UserData")
            scrmService.postClient(
                token = token,
                body = ClientsEntity(
                    name = UserData.userName.name,
                    soname = UserData.userName.soname,
                    patronymic = UserData.userName.patronymic,
                    dateOfBirth = UserData.dateOfBirthday.ifEmpty { "0001-01-01T00:00:00.000Z" },
                    sex = when (income.sex) {
                        Sex.FEMALE -> TypeSex.FEMALE
                        Sex.MALE -> TypeSex.MALE
                        else -> null
                    }
                )
            )
            scrmService.postClientEmail(
                token = token,
                body = IncomeDataChannel(
                    data = UserData.email
                )
            )
        }
    }
}