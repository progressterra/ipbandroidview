package com.progressterra.ipbandroidview.domain.mapper

import com.progressterra.ipbandroidapi.api.checklist.model.RFCheckViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.base.ManageResources
import com.progressterra.ipbandroidview.base.Mapper
import com.progressterra.ipbandroidview.ui.organizations.Organization

interface AuditMapper : Mapper<RFCheckViewModel, Organization> {
//    class Base(private val manageResources: ManageResources) : AuditMapper {
//
//        override fun map(data: RFCheckViewModel): Organization = Organization(auditName = data.name ?: manageResources.string(
//            R.string.no_data), address = data.address ?: manageResources.string(
//            R.string.no_data)
//    }
}