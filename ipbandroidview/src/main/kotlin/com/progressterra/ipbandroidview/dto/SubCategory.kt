package com.progressterra.ipbandroidview.dto

import com.progressterra.ipbandroidview.dto.component.Id
import com.progressterra.ipbandroidview.dto.component.Name

data class SubCategory(
    val hasNext: Boolean,
    override val id: String,
    override val name: String
) : Id, Name
