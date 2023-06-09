package com.progressterra.ipbandroidview.widgets.documents

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidapi.api.documents.models.TypeValueCharacteristic
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

interface DocumentsUseCase {

    suspend operator fun invoke(): Result<DocumentsState>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: DocumentsRepository,
        private val gson: Gson,
    ) : AbstractTokenUseCase(scrmRepository, provideLocation), DocumentsUseCase {

        override suspend fun invoke(): Result<DocumentsState> = withToken { token ->
            DocumentsState(items = repo.docsBySpecification(token, UserData.docSpecId)
                .getOrThrow()?.listProductCharacteristic?.map { doc ->
                    val entries = gson.fromJson<List<FieldData>?>(
                        doc.characteristicType?.dataInJSON,
                        object : TypeToken<List<FieldData>>() {}.type
                    ).map {
                        TextFieldState(
                            id = "doc${it.idrfCharacteristicType}",
                            text = it.valueData,
                            hint = it.comment
                        )
                    }
                    DocumentsState.Item(
                        status = doc.characteristicValue?.statusDoc ?: TypeStatusDoc.NOT_FILL,
                        name = doc.characteristicType?.name ?: "",
                        id = doc.characteristicValue?.idUnique!!,
                        entries = entries
                    )
                } ?: emptyList())
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