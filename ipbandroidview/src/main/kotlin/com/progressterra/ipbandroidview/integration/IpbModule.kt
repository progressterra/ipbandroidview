package com.progressterra.ipbandroidview.integration

import com.progressterra.ipbandroidview.features.featuresModule
import com.progressterra.ipbandroidview.pages.pagesModule
import com.progressterra.ipbandroidview.widgets.widgetsModule
import org.koin.dsl.module


@Suppress("unused")
val iPBAndroidViewModule = module {

    includes(pagesModule, widgetsModule, featuresModule)
}