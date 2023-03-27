package com.progressterra.ipbandroidview.ui.catalog

import com.progressterra.ipbandroidview.composable.component.CatalogBarComponentEvent
import com.progressterra.ipbandroidview.shared.ui.TextFieldEvent
import com.progressterra.ipbandroidview.composable.component.UseCatalogBarComponent
import com.progressterra.ipbandroidview.model.CategoryWithSubcategories

interface CatalogInteractor : UseCatalogBarComponent {

    fun refresh()

    fun openCategory(categoryWithSubcategories: CategoryWithSubcategories)

    class Empty : CatalogInteractor {

        override fun handle(id: String, event: CatalogBarComponentEvent) = Unit

        override fun handle(id: String, event: TextFieldEvent) = Unit

        override fun refresh() = Unit

        override fun openCategory(categoryWithSubcategories: CategoryWithSubcategories) = Unit
    }
}