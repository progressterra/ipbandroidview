package com.progressterra.ipbandroidview.widgets.documents

import android.util.Log
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidapi.api.documents.models.TypeValueCharacteristic
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoState
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
            DocumentsState(items = repo.docsBySpecification(token, UserData.citizenship.id)
                .getOrThrow()?.listProductCharacteristic?.map { doc ->
                    Log.d("DOC", doc.characteristicType?.dataInJSON ?: "NULL")
                    val entries = gson.fromJson<List<FieldData>?>(
                        doc.characteristicType?.dataInJSON,
                        object : TypeToken<List<FieldData>>() {}.type
                    ).map {
                        TextFieldState(
                            id = it.order.toString(),
                            text = it.valueData,
                            hint = it.comment
                        )
                    }
                    DocumentsState.Item(
                        status = doc.characteristicValue?.statusDoc ?: TypeStatusDoc.NOT_FILL,
                        name = doc.characteristicType?.name ?: "",
                        id = doc.characteristicValue?.idUnique!!,
                        entries = entries,
                        photo = if (doc.imageRequired!!) DocumentPhotoState(
                            items = doc.characteristicValue?.listImages?.map { img ->
                                MultisizedImage(
                                    id = img.idUnique!!,
                                    local = false,
                                    toRemove = false,
                                    url = img.urlData!!
                                )
                            } ?: emptyList()) else null
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