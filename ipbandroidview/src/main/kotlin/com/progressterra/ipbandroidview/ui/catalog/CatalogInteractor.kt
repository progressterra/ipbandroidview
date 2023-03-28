package com.progressterra.ipbandroidview.ui.catalog

import com.progressterra.ipbandroidview.composable.component.CatalogBarComponentEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.composable.component.UseCatalogBarComponent
import com.progressterra.ipbandroidview.model.CategoryWithSubcategories

interface CatalogInteractor : UseCatalogBarComponent {

    fun refresh()

    fun openCategory(categoryWithSubcategories: CategoryWithSubcategories)

    class Empty : CatalogInteractor {

        override fun handle(event: CatalogBarComponentEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit

        override fun refresh() = Unit

        override fun openCategory(categoryWithSubcategories: CategoryWithSubcategories) = Unit
    }
}