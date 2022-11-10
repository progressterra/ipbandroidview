package com.progressterra.ipbandroidview.model

import android.os.Parcelable
import com.progressterra.ipbandroidview.components.SubCategoryState
import com.progressterra.ipbandroidview.model.component.HasNext
import com.progressterra.ipbandroidview.model.component.SubCategories
import kotlinx.parcelize.Parcelize

interface SubCategory : Parcelable, SubCategories, HasNext, SubCategoryState {

    @Parcelize
    data class Base(
        override val id: String,
        override val name: String,
        override val subCategories: List<SubCategory>,
        override val hasNext: Boolean
    ) : SubCategory
}
