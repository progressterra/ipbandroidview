package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidview.features.currentcitizenship.CurrentCitizenshipState
import com.progressterra.ipbandroidview.features.dialogpicker.DialogPickerState
import com.progressterra.ipbandroidview.processes.data.CitizenshipRepository
import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.shared.UserData

interface FetchCitizenshipsUseCase {

    suspend operator fun invoke(): Result<CurrentCitizenshipState>

    class Base(
        private val citizenshipRepository: CitizenshipRepository
    ) : FetchCitizenshipsUseCase, AbstractLoggingUseCase() {

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