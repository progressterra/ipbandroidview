package com.progressterra.ipbandroidview.processes.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistService
import com.progressterra.ipbandroidview.entities.Organization
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase

interface AllOrganizationsUseCase {

    suspend operator fun invoke(): Result<List<Organization>>

    class Base(
        private val checklistService: ChecklistService,
        manageResources: ManageResources,
        makeToastUseCase: MakeToastUseCase,
        obtainAccessToken: ObtainAccessToken
    ) : AllOrganizationsUseCase, AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources) {

        override suspend fun invoke(): Result<List<Organization>> = withToken { token ->
            val places = checklistService.availableChecklistsAndDocs(token).dataList ?: emptyList()
            places.map { place ->
                Organization(
                    address = place.address ?: "",
                    id = place.idUnique!!,
                    name = place.name ?: "",
                    imageUrl = place.listImages?.firstOrNull()?.urlData ?: "",
                    audits = (place.countAvailableRFCheck ?: 0).toString(),
                    documents = (place.countDHCheckPerformedForExecution ?: 0).toString(),
                    latitude = place.latitude ?: 0.0,
                    longitude = place.longitude ?: 0.0
                )
            }
        }
    }
}