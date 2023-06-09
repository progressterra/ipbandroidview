package com.progressterra.ipbandroidview.pages.documentdetails

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.documents.models.FieldData
import com.progressterra.ipbandroidapi.api.documents.models.IncomeDataClientArea
import com.progressterra.ipbandroidapi.api.documents.models.TypeValueCharacteristic
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.FileExplorer
import com.progressterra.ipbandroidview.shared.ui.textfield.TextInputType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

interface SaveDocumentsUseCase {

    suspend operator fun invoke(data: DocumentDetailsState): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: DocumentsRepository,
        private val fileExplorer: FileExplorer,
        private val gson: Gson,
    ) : AbstractTokenUseCase(scrmRepository, provideLocation), SaveDocumentsUseCase {

        override suspend fun invoke(data: DocumentDetailsState): Result<Unit> = withToken { token ->
            val entries = data.entries.map {
                FieldData(
                    idrfCharacteristicType = data.id,
                    name = it.label,
                    comment = it.placeholder,
                    order = 0,
                    typeValue = when (it.type) {
                        TextInputType.DEFAULT -> TypeValueCharacteristic.AS_STRING
                        TextInputType.NUMBER -> TypeValueCharacteristic.AS_NUMBER
                        TextInputType.PHONE_NUMBER -> TypeValueCharacteristic.AS_STRING
                        TextInputType.CHAT -> TypeValueCharacteristic.AS_STRING
                    },
                    valueData = it.text

                )
            }
            repo.setValueForChar(
                token, data.id, IncomeDataClientArea(
                    data = gson.toJson(entries, object : TypeToken<List<FieldData>>() {}.type)
                )
            )
            data.photo?.let {
                it.items.filter { photo -> photo.local }.forEach { img ->
                    repo.setImageForChar(
                        token, data.id, MultipartBody.Part.createFormData(
                            name = "file",
                            filename = fileExplorer.pictureFile(img.id).name,
                            body = fileExplorer.pictureFile(img.id)
                                .asRequestBody("image/*".toMediaTypeOrNull())
                        )
                    )
                }
            }
        }
    }
}