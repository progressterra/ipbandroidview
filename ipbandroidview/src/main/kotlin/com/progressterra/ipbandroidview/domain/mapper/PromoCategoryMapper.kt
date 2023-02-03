package com.progressterra.ipbandroidview.domain.mapper

import com.progressterra.ipbandroidapi.api.ipbfavpromorec.model.RFKindOfEntity
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.DoubleMapper
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.model.store.Category
import com.progressterra.ipbandroidview.model.store.SimpleCategory

interface PromoCategoryMapper : DoubleMapper<RFKindOfEntity, String, Category> {

    class Base(
        manageResources: ManageResources
    ) : PromoCategoryMapper {

        private val noData = manageResources.string(R.string.no_data)

        override fun map(data1: RFKindOfEntity, data2: String): Category = SimpleCategory(
            id = data2,
            name = data1.title ?: noData
        )
    }
}