package com.progressterra.ipbandroidview.processes.docs

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.documents.models.FieldData
import com.progressterra.ipbandroidapi.api.documents.models.IncomeDataClientArea
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.processes.media.FileExplorer
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.throwOnFailure
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

interface SaveDocumentsUseCase {

    suspend operator fun invoke(data: Document): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val repo: DocumentsRepository,
        private val fileExplorer: FileExplorer,
        private val gson: Gson, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources,
    ) : AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources),
        SaveDocumentsUseCase {

        override suspend fun invoke(data: Document): Result<Unit> = withToken { token ->
            val entries = data.entries.map { it.toFieldData(data.id) }
            repo.setValueForChar(
                token, data.id, IncomeDataClientArea(
                    data = gson.toJson(entries, object : TypeToken<List<FieldData>>() {}.type)
                )
            ).throwOnFailure()
            if (!data.photo.isEmpty()) {
                data.photo.items.filter { photo -> photo.local }.forEach { img ->
                    repo.setImageForChar(
                        token, data.id, MultipartBody.Part.createFormData(
                            name = "file",
                            filename = "${img.id}.jpg",
                            body = fileExplorer.file("${img.id}.jpg")
                                .asRequestBody("image/*".toMediaTypeOrNull())
                        )
                    ).throwOnFailure()
                }
            }
        }
    }
}