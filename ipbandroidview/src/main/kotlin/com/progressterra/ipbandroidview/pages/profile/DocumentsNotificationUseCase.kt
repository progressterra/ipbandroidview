package com.progressterra.ipbandroidview.pages.profile

import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData

interface DocumentsNotificationUseCase {

    suspend operator fun invoke(): Result<ProfileState.CounterNotification>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val repo: DocumentsRepository
    ) : AbstractTokenUseCase(scrmRepository, provideLocation), DocumentsNotificationUseCase {

        override suspend fun invoke(): Result<ProfileState.CounterNotification> =
            withToken { token ->
                if (UserData.citizenship.isEmpty()) {
                    ProfileState.CounterNotification()
                } else {
                    val result =
                        repo.docsBySpecification(token, UserData.citizenship.id).getOrThrow()
                    ProfileState.CounterNotification(
                        count = result?.listProductCharacteristic?.count { it.characteristicValue?.statusDoc == TypeStatusDoc.CONFIRMED }
                            ?: 0,
                        max = result?.listProductCharacteristic?.size ?: 0
                    )
                }
            }
    }
}