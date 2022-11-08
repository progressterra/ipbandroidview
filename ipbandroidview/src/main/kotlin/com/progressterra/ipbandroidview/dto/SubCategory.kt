package com.progressterra.ipbandroidview.dto

import com.progressterra.ipbandroidview.components.SubCategoryState
import com.progressterra.ipbandroidview.dto.component.HasNext
import com.progressterra.ipbandroidview.dto.component.Id
import com.progressterra.ipbandroidview.dto.component.Name
import com.progressterra.ipbandroidview.dto.component.SubCategories

data class SubCategory(
    override val id: String,
    override val name: String,
    override val subCategories: List<SubCategory>,
    override val hasNext: Boolean
) : SubCategories, HasNext, SubCategoryState
