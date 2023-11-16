package com.progressterra.ipbandroidview.processes.docs

import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidview.pages.profile.ProfileScreenState
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData

interface DocumentsNotificationUseCase {

    suspend operator fun invoke(): Result<ProfileScreenState.CounterNotification>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val repo: DocumentsRepository, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources),
        DocumentsNotificationUseCase {

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