package com.progressterra.ipbandroidview.pages.documentdetails

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.documents.models.FieldData
import com.progressterra.ipbandroidapi.api.documents.models.IncomeDataClientArea
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.entities.toFieldData
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.FileExplorer
import com.progressterra.ipbandroidview.shared.throwOnFailure
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

interface SaveDocumentsUseCase {

    suspend operator fun invoke(data: Document): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: DocumentsRepository,
        private val fileExplorer: FileExplorer,
        private val gson: Gson,
    ) : AbstractTokenUseCase(scrmRepository, provideLocation), SaveDocumentsUseCase {

        override suspend fun invoke(data: Document): Result<Unit> = withToken { token ->
            val entries = data.entries.map { it.toFieldData(data.id) }
            repo.setValueForChar(
                token, data.id, IncomeDataClientArea(
                    data = gson.toJson(entries, object : TypeToken<List<FieldData>>() {}.type)
                )
            ).throwOnFailure()
            data.photo?.let {
                it.items.filter { photo -> photo.local }.forEach { img ->
                    repo.setImageForChar(
                        token, data.id, MultipartBody.Part.createFormData(
                            name = "file",
                            filename = fileExplorer.pictureFile(img.id).name,
                            body = fileExplorer.pictureFile(img.id)
                                .asRequestBody("image/*".toMediaTypeOrNull())
                        )
                    ).throwOnFailure()
                }
            }
        }
    }
}