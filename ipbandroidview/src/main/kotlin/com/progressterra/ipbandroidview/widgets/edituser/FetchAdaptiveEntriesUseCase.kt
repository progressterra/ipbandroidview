package com.progressterra.ipbandroidview.widgets.edituser

import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.AdaptiveEntry
import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.features.makephoto.MakePhotoState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

interface FetchAdaptiveEntriesUseCase {

    suspend operator fun invoke(citizenshipId: String): Result<List<AdaptiveEntry>>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val repo: DocumentsRepository
    ) : AbstractTokenUseCase(scrmRepository, provideLocation), FetchAdaptiveEntriesUseCase {

        override suspend fun invoke(citizenshipId: String): Result<List<AdaptiveEntry>> =
            withToken { token ->
                repo.docsBySpecification(token, citizenshipId)
                    .getOrThrow()?.listProductCharacteristic?.filter { it.characteristicValue?.statusDoc == TypeStatusDoc.NOT_FILL || it.characteristicValue?.statusDoc == TypeStatusDoc.REJECTED }
                    ?.map {
                        AdaptiveEntry(
                            makePhoto = if (it.imageRequired!!) MakePhotoState(
                                makePhoto = ButtonState(id = "makePhoto${it.characteristicValue?.idUnique!!}"),
                                items = it.characteristicValue?.listImages?.map { img ->
                                    MultisizedImage(
                                        id = img.idUnique!!,
                                        local = false,
                                        toRemove = false,
                                        thumbnail = img.urlData!!,
                                        fullSize = img.urlData!!
                                    )
                                } ?: emptyList()) else null,
                            status = it.characteristicValue?.statusDoc ?: TypeStatusDoc.NOT_FILL,
                            name = it.characteristicType?.name ?: "",
                            id = it.characteristicValue?.idUnique!!,
                            text = TextFieldState(
                                id = it.characteristicValue?.idUnique!!,
                                text = it.characteristicValue?.valueAsString ?: ""
                            ))
                    } ?: emptyList()
            }
    }
}
