package com.progressterra.ipbandroidview.integration

import com.progressterra.ipbandroidapi.di.iPBAndroidAPIModule
import com.progressterra.ipbandroidview.features.featuresModule
import com.progressterra.ipbandroidview.pages.pagesModule
import com.progressterra.ipbandroidview.processes.processesModule
import com.progressterra.ipbandroidview.shared.sharedModule
import com.progressterra.ipbandroidview.widgets.widgetsModule
import org.koin.dsl.module


@Suppress("unused")
val ipbModule = module {

    includes(
        iPBAndroidAPIModule,
        pagesModule,
        widgetsModule,
        featuresModule,
        sharedModule,
        processesModule
    )
}