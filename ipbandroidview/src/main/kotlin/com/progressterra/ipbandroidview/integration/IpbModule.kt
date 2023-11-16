package com.progressterra.ipbandroidview.integration

import com.progressterra.ipbandroidapi.iPBAndroidAPIModule
import com.progressterra.ipbandroidview.pages.pagesModule
import com.progressterra.ipbandroidview.processes.processesModule
import com.progressterra.ipbandroidview.shared.sharedModule
import org.koin.dsl.module


@Suppress("unused")
val ipbModule = module {
    includes(
        iPBAndroidAPIModule,
        pagesModule,
        sharedModule,
        processesModule
    )
}