package com.progressterra.ipbandroidview.widgets.documents

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.documents.models.FieldData
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
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
            if (UserData.citizenship.isEmpty()) {
                DocumentsState()
            }
            DocumentsState(items = repo.docsBySpecification(token, UserData.citizenship.id)
                .getOrThrow()?.listProductCharacteristic?.map { doc ->
                    Log.d("DOC", doc.characteristicType?.dataInJSON ?: "NULL")
                    val entries = (doc.characteristicType?.dataInJSON?.let {
                        gson.fromJson<List<FieldData>?>(
                            it,
                            object : TypeToken<List<FieldData>>() {}.type
                        )
                    } ?: emptyList()).map {
                        TextFieldState(
                            id = it.order.toString(),
                            text = it.valueData ?: "",
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
    }
}