package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidview.entities.Sex
import com.progressterra.ipbandroidview.entities.formatZdt
import com.progressterra.ipbandroidview.entities.parseToZDT
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState

interface FetchUserUseCase {

    suspend operator fun invoke(): Result<EditUserState>

    class Base(makeToastUseCase: MakeToastUseCase, manageResources: ManageResources) :
        FetchUserUseCase, AbstractLoggingUseCase(
        makeToastUseCase, manageResources
    ) {

        override suspend fun invoke(): Result<EditUserState> = handle {
            val editUser = EditUserState()
            editUser.copy(
                name = editUser.name.unFormatByType(UserData.userName.name),
                soname = editUser.soname.unFormatByType(UserData.userName.soname),
                patronymic = editUser.patronymic.unFormatByType(UserData.userName.patronymic),
                email = editUser.email.unFormatByType(UserData.email),
                birthday = editUser.birthday.unFormatByType(
                    UserData.dateOfBirthday.parseToZDT()?.formatZdt("dd.MM.yyyy") ?: ""
                ),
                sex = when (UserData.sex) {
                    1 -> Sex.MALE
                    2 -> Sex.FEMALE
                    else -> null
                }
            )
        }
    }
}