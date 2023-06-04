package com.progressterra.ipbandroidview.processes.user

import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.FileExplorer
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.UserName
import com.progressterra.ipbandroidview.shared.splitName
import com.progressterra.ipbandroidview.shared.toEpochMillis
import com.progressterra.ipbandroidview.widgets.edituser.CitizenshipSuggestionsUseCase
import com.progressterra.ipbandroidview.widgets.edituser.EditUserState
import okhttp3.MultipartBody

interface SaveDataUseCase {

    suspend operator fun invoke(income: EditUserState): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        private val repo: DocumentsRepository,
        private val citizenshipSuggestionsUseCase: CitizenshipSuggestionsUseCase,
        private val fileExplorer: FileExplorer,
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
            UserData.docSpecId =
                citizenshipSuggestionsUseCase(income.citizenship.text).items.first { it.name == income.citizenship.text }.data
            income.adaptiveDocuments.forEach { doc ->
                if (doc.makePhoto != null) {
                    doc.makePhoto.items.filter { it.local }.forEach { img ->
                        repo.setImageForChar(token, img.id, MultipartBody.Part fileExplorer.pictureFile(img.id))
                    }
                }
            }
        }
    }
}