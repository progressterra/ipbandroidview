package com.progressterra.ipbandroidview.domain.updateuserinfo

import java.time.LocalDate

interface UpdatePersonalInfoUseCase {

    suspend fun update(name: String, email: String, birthdayDate: LocalDate): Result<Unit>
}