package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidview.pages.profile.ProfileScreenState
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData

interface DocumentsNotificationUseCase {

    suspend operator fun invoke(): Result<ProfileScreenState.CounterNotification>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val repo: DocumentsRepository
    ) : AbstractTokenUseCase(obtainAccessToken), DocumentsNotificationUseCase {

        override suspend fun invoke(): Result<ProfileScreenState.CounterNotification> =
            withToken { token ->
                if (UserData.citizenship.isEmpty()) {
                    ProfileScreenState.CounterNotification()
                } else {
                    val result =
                        repo.docsBySpecification(token, UserData.citizenship.id).getOrThrow()
                    ProfileScreenState.CounterNotification(
                        count = result?.listProductCharacteristic?.count { it.characteristicValue?.statusDoc == TypeStatusDoc.CONFIRMED }
                            ?: 0,
                        max = result?.listProductCharacteristic?.size ?: 0
                    )
                }
            }
    }
}