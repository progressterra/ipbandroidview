package com.progressterra.ipbandroidview.processes.mapper

import com.progressterra.ipbandroidapi.api.product.models.FieldForFilter
import com.progressterra.ipbandroidapi.api.product.models.TypeComparison
import com.progressterra.ipbandroidview.core.Mapper
import com.progressterra.ipbandroidview.entities.Filter

interface GoodsFilterMapper : Mapper<Filter, FieldForFilter> {

    class Base : GoodsFilterMapper {

        override fun map(data: Filter): FieldForFilter = FieldForFilter(
            fieldName = data.key,
            listValue = data.values,
            comparisonType = TypeComparison.CONTAINS_IGNORE_CASE
        )
    }
}