package com.progressterra.ipbandroidview.domain.usecase.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.complace.ComPlaceRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.model.Organization
import com.progressterra.ipbandroidapi.api.checklist.model.FilterAndSort as ChecklistF
import com.progressterra.ipbandroidapi.api.complace.models.FilterAndSort as ComPlaceF

interface AllOrganizationsUseCase {

    suspend operator fun invoke(): Result<List<Organization>>

    class Base(
        private val placeRepository: ComPlaceRepository,
        private val checklistRepository: ChecklistRepository,
        sCRMRepository: SCRMRepository,
        manageResources: ManageResources,
        provideLocation: ProvideLocation
    ) : AllOrganizationsUseCase, AbstractUseCase(sCRMRepository, provideLocation) {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun invoke(): Result<List<Organization>> = withToken { token ->
            val places = placeRepository.places(
                token, ComPlaceF(emptyList(), null, "", 0, 300)
            ).getOrThrow()!!
            buildList {
                places.map { place ->
                    val audits =
                        checklistRepository.availableChecklistsForPlace(token, place.idUnique!!)
                            .getOrThrow()!!
                    val docs = checklistRepository.availableDocsForPlace(
                        token, place.idUnique!!, ChecklistF(
                            emptyList(), null, "", false, 0, 300
                        )
                    ).getOrThrow()!!
                    add(
                        Organization(
                            address = place.address ?: noData,
                            id = place.idUnique!!,
                            name = place.name ?: noData,
                            imageUrl = place.listImages?.first()?.urlData ?: "",
                            audits = audits.size.toString(),
                            documents = docs.size.toString(),
                            latitude = place.latitude ?: 0.0,
                            longitude = place.longitude ?: 0.0
                        )
                    )
                }
            }
        }
    }
}