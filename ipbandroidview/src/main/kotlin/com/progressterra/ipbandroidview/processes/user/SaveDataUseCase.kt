package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.documents.models.IncomeDataClientArea
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.processes.data.CitizenshipRepository
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.FileExplorer
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.UserName
import com.progressterra.ipbandroidview.shared.splitName
import com.progressterra.ipbandroidview.shared.toEpochMillis
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

interface SaveDataUseCase {

    suspend operator fun invoke(income: EditUserState): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        private val repo: DocumentsRepository,
        private val fileExplorer: FileExplorer,
        private val citizenshipRepository: CitizenshipRepository,
        provideLocation: ProvideLocation
    ) : SaveDataUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(income: EditUserState): Result<Unit> = withToken { token ->
            val nameList = income.name.text.splitName(false)
            UserData.userName = UserName(
                name = nameList[0], surname = nameList[1]
            )
            UserData.dateOfBirthday = income.birthday.text.toEpochMillis()
            UserData.phone = income.phone.text
            UserData.email = income.email.text
            UserData.citizenship = income.citizenship.text
            UserData.docSpecId = citizenshipRepository.idByName(income.citizenship.text)
            income.adaptiveDocuments.forEach { doc ->
                if (doc.makePhoto != null) {
                    doc.makePhoto.items.filter { it.local }.forEach { img ->
                        repo.setImageForChar(
                            token, doc.id, MultipartBody.Part.createFormData(
                                name = "file",
                                filename = fileExplorer.pictureFile(img.id).name,
                                body = fileExplorer.pictureFile(img.id)
                                    .asRequestBody("image/*".toMediaTypeOrNull())
                            )
                        )
                    }
                }
                repo.setValueForChar(
                    token, doc.id, IncomeDataClientArea(
                        data = doc.text.text
                    )
                )
            }
        }
    }
}