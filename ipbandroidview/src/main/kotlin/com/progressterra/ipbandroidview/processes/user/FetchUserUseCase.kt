package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.print
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState
import com.progressterra.ipbandroidview.widgets.edituser.FetchAdaptiveEntriesUseCase
import com.progressterra.ipbandroidview.widgets.edituser.uBirthdayText
import com.progressterra.ipbandroidview.widgets.edituser.uCitizenshipText
import com.progressterra.ipbandroidview.widgets.edituser.uEmailText
import com.progressterra.ipbandroidview.widgets.edituser.uNameText
import com.progressterra.ipbandroidview.widgets.edituser.uPhoneText
import java.util.Date

interface FetchUserUseCase {

    suspend operator fun invoke(): Result<EditUserState>

    class Base(
        private val fetchAdaptiveEntriesUseCase: FetchAdaptiveEntriesUseCase,
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository
    ) : FetchUserUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<EditUserState> = withToken {
            var result = EditUserState()
                .uNameText(buildString {
                    if (UserData.userName.name.isNotBlank()) append(UserData.userName.name)
                    if (UserData.userName.surname.isNotBlank()) append(" ${UserData.userName.surname}")
                })
                .uEmailText(UserData.email)
                .uPhoneText(UserData.phone)
                .uCitizenshipText(UserData.citizenship)
                .uBirthdayText(Date(UserData.dateOfBirthday).print())
            if (UserData.citizenship.isNotBlank() && UserData.docSpecId.isNotBlank()) {
                result =
                    result.copy(adaptiveDocuments = fetchAdaptiveEntriesUseCase(UserData.docSpecId).getOrThrow())
            }
            result
        }
    }
}