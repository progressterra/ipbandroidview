package com.progressterra.ipbandroidview.pages.documentdetails

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.documents.models.IncomeDataClientArea
import com.progressterra.ipbandroidapi.api.documents.models.TypeValueCharacteristic
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.FileExplorer
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
                    name = it.hint!!,
                    comment = it.hint,
                    order = 0,
                    typeValue = TypeValueCharacteristic.AS_STRING,
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

        data class FieldData(
            // Значение харатеристики (к какому типу документа относится поле), на основе который создано поле
            @SerializedName("idrfCharacteristicType") val idrfCharacteristicType: String,
            // Наименование поля
            @SerializedName("name") val name: String,
            // Подсказка, которую можно отобразить пользователю
            @SerializedName("comment") val comment: String,
            // Позволяет организовать сортировку данных
            @SerializedName("order") val order: Int,
            // Тип значения поля. Позволяет понять какие данные хранятся в поле. Позволяет правильного отображать значения полей
            @SerializedName("typeValue") val typeValue: TypeValueCharacteristic,
            // Само значение
            @SerializedName("valueData") val valueData: String
        )
    }
}