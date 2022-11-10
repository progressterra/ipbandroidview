package com.progressterra.ipbandroidview.ui.subcatalog

import com.progressterra.ipbandroidview.actions.Back
import com.progressterra.ipbandroidview.model.SubCategory

interface SubCatalogInteractor : Back {

    fun subCategory(subCategory: SubCategory)

    class Empty : SubCatalogInteractor {

        override fun back() = Unit

        override fun subCategory(subCategory: SubCategory) = Unit
    }
}