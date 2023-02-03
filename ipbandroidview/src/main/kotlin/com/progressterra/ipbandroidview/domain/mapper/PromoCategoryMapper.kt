package com.progressterra.ipbandroidview.domain.mapper

import com.progressterra.ipbandroidapi.api.ipbfavpromorec.model.RFKindOfEntity
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.Mapper
import com.progressterra.ipbandroidview.model.store.Category
import com.progressterra.ipbandroidview.model.store.SimpleCategory

interface PromoCategoryMapper : Mapper<RFKindOfEntity, Category> {

    class Base(
        manageResources: ManageResources
    ) : PromoCategoryMapper {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data: RFKindOfEntity): Category = SimpleCategory(
            id = data.dataForDefaultLinkTo!!,
            name = data.title ?: noData
        )
    }
}