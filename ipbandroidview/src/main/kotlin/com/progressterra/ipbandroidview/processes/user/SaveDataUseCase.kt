package com.progressterra.ipbandroidview.processes.user

import android.util.Log
import com.progressterra.ipbandroidapi.api.ipbmediadata.IPBMediaDataRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.FileExplorer
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.UserName
import com.progressterra.ipbandroidview.shared.splitName
import com.progressterra.ipbandroidview.shared.toAddress
import com.progressterra.ipbandroidview.shared.toEpochMillis
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

interface SaveDataUseCase {

    suspend operator fun invoke(income: EditUserState): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        private val mediaDataRepository: IPBMediaDataRepository,
        private val fileExplorer: FileExplorer,
        provideLocation: ProvideLocation
    ) : SaveDataUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(income: EditUserState): Result<Unit> = withToken { token ->
            val nameList = income.name.text.splitName(false)
            UserData.userName = UserName(
                name = nameList[0], surname = nameList[1]
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
            UserData.passportProviderCode = income.passportCode.text
            UserData.patent = income.patent.text
            income.makePhoto.items.forEach {
                Log.d("CAMERA", "save invoke ${it.id}")
                mediaDataRepository.attachToEntity(
                    accessToken = token,
                    typeContent = "image",
                    alias = "docs",
                    tag = 0,
                    file = MultipartBody.Part.createFormData(
                        name = "file",
                        filename = fileExplorer.pictureFile(it.id).name,
                        body = fileExplorer.pictureFile(it.id)
                            .asRequestBody("image/*".toMediaTypeOrNull())
                    )
                )
            }
        }
    }
}