package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidview.domain.mapper.AuditMapper
import com.progressterra.ipbandroidview.ui.organizations.Audit

interface AllAuditsUseCase {

    suspend fun allAudits(): Result<List<Audit>>

    class Base(private val repo: ChecklistRepository, private val mapper: AuditMapper) :
        AllAuditsUseCase {

        override suspend fun allAudits(): Result<List<Audit>> {
            val responseAllPlaces = repo.availableChecklists()
            if (responseAllPlaces.isFailure)
                return Result.failure(responseAllPlaces.exceptionOrNull()!!)
            val responseAllChecklists = buildList<Audit> {
                responseAllPlaces.getOrNull()!!.forEach { place ->
                    place.idUnique?.let {
                        repo.availableChecklistsForPlace(it).map {

                        }
                    }

                }
            }
            responseAllPlaces.getOrNull()
        }
    }
}