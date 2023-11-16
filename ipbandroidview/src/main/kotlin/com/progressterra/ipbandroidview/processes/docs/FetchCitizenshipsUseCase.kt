package com.progressterra.ipbandroidview.processes.docs

import com.progressterra.ipbandroidview.features.currentcitizenship.CurrentCitizenshipState
import com.progressterra.ipbandroidview.features.dialogpicker.DialogPickerState
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.shared.UserData

interface FetchCitizenshipsUseCase {

    suspend operator fun invoke(): Result<CurrentCitizenshipState>

    class Base(
        private val citizenshipRepository: CitizenshipRepository, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : FetchCitizenshipsUseCase, AbstractLoggingUseCase(makeToastUseCase, manageResources) {

        override suspend fun invoke(): Result<CurrentCitizenshipState> = handle {
            val variants = citizenshipRepository.citizenships()
            CurrentCitizenshipState(
                citizenship = if (UserData.citizenship.isEmpty()) null else UserData.citizenship,
                dialog = DialogPickerState(variants = variants,
                    selected = variants.firstOrNull { it == UserData.citizenship })
            )
        }
    }
}