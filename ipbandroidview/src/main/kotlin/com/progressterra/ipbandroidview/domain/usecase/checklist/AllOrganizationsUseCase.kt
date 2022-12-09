package com.progressterra.ipbandroidview.domain.usecase.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.model.checklist.Organization

interface AllOrganizationsUseCase {

    suspend operator fun invoke(): Result<List<Organization>>

    class Base(
        private val repo: ChecklistRepository,
        sCRMRepository: SCRMRepository,
        manageResources: ManageResources,
        provideLocation: ProvideLocation
    ) : AllOrganizationsUseCase, AbstractUseCase(sCRMRepository, provideLocation) {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun invoke(): Result<List<Organization>> = withToken { token ->
            val places = repo.availableChecklistsAndDocs(token).getOrThrow()
            buildList {
                places?.map { place ->
                    add(
                        Organization(
                            address = place.address ?: noData,
                            id = place.idUnique!!,
                            name = place.name ?: noData,
                            imageUrl = place.imageURL ?: "",
                            audits = place.countAvailableRFCheck?.toString() ?: noData,
                            documents = place.countDHCheckPerformedForExecution?.toString()
                                ?: noData,
                            latitude = place.latitude ?: 0.0,
                            longitude = place.longitude ?: 0.0
                        )
                    )
                }
            }
        }
    }
}