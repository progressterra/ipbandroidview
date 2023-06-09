package com.progressterra.ipbandroidview.pages.documentdetails

import androidx.core.text.isDigitsOnly
import com.progressterra.ipbandroidapi.api.documents.models.TypeValueCharacteristic
import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.shared.isDate

interface ValidationUseCase {

    suspend operator fun invoke(state: DocumentDetailsState): Result<Unit>

    class Base : ValidationUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(state: DocumentDetailsState): Result<Unit> = handle {
            state.entries.forEach {
                when (it.typeValue) {
                    TypeValueCharacteristic.AS_STRING -> Unit
                    TypeValueCharacteristic.AS_NUMBER ->
                        if (!it.text.isDigitsOnly()) {
                           throw Exception("Invalid!")
                        }

                    TypeValueCharacteristic.AS_DATE_TIME ->
                        if (!it.text.isDate()) {
                            throw Exception("Invalid!")
                        }
                    TypeValueCharacteristic.AS_REFERENCE -> Unit
                    TypeValueCharacteristic.AS_CUSTOM_AS_JSON -> Unit
                    else -> Unit
                }
            }
            if (state.photo?.items?.isEmpty() == true) {
                throw Exception("Invalid!")
            }
        }
    }
}