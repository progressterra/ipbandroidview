package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.data.ProvideLocation
import com.progressterra.ipbandroidview.ui.organizations.Organization

//TODO warnings

interface AllOrganizationsUseCase {

    suspend fun allOrganizations(): Result<List<Organization>>

    class Base(
        private val repo: ChecklistRepository,
        sCRMRepository: SCRMRepository,
        manageResources: ManageResources,
        provideLocation: ProvideLocation
    ) : AllOrganizationsUseCase, AbstractUseCaseWithToken(sCRMRepository, provideLocation) {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun allOrganizations(): Result<List<Organization>> = runCatching {
            val places = withToken {
                repo.availableChecklistsAndDocs(it)
            }.getOrThrow()
            buildList {
                places?.map { place ->
                    add(
                        Organization(
                            place.address ?: noData,
                            place.idUnique!!,
                            0,
                            place.name ?: noData,
                            place.imageURL ?: "",
                            place.latitude ?: 0.0,
                            place.longitude ?: 0.0
                        )
                    )
                }
            }
        }
    }
}