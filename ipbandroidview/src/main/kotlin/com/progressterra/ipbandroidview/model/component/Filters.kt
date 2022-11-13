package com.progressterra.ipbandroidview.model.component

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.model.Filter

@Immutable
interface Filters {

    val filters: List<Filter>
}