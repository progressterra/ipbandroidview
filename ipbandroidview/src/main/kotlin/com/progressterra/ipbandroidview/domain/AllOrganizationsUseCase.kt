package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.Constants
import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.base.ManageResources
import com.progressterra.ipbandroidview.ui.organizations.Organization

interface AllOrganizationsUseCase {

    suspend fun allOrganizations(): List<Organization>

    class Base(private val repo: ChecklistRepository, manageResources: ManageResources) :
        AllOrganizationsUseCase {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun allOrganizations(): List<Organization> = buildList {
            repo.availableChecklists().map { places ->
                places?.map { place ->
                    add(
                        Organization(
                            place.address ?: noData,
                            place.idUnique?.toString() ?: Constants.DEFAULT_ID,
                            place.countAvailableRFCheck ?: 0,
                            place.name ?: noData,
                            place.imageURL ?: ""
                        )
                    )
                }
            }
        }
    }
}