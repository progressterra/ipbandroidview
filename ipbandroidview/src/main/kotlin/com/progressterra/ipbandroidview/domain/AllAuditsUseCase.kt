package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidview.domain.mapper.AuditMapper
import com.progressterra.ipbandroidview.ui.organizations.Organization

interface AllAuditsUseCase {

//    suspend fun allAudits(): Result<List<Organization>>
//
//    class Base(private val repo: ChecklistRepository, private val mapper: AuditMapper) :
//        AllAuditsUseCase {
//
//        override suspend fun allAudits(): Result<List<Organization>> {
//            val responseAllPlaces = repo.availableChecklists()
//            if (responseAllPlaces.isFailure)
//                return Result.failure(responseAllPlaces.exceptionOrNull()!!)
//            val responseAllChecklists = buildList<Organization> {
//                responseAllPlaces.getOrNull()!!.forEach { place ->
//                    place.idUnique?.let { uuid ->
//                        repo.availableChecklistsForPlace(uuid).map { list ->
//                            list.map {
//                                it.
//                            }
//                        }
//                    }
//
//                }
//            }
//            responseAllPlaces.getOrNull()
//        }
//    }
}