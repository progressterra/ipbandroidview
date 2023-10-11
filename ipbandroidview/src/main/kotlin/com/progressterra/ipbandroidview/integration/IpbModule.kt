package com.progressterra.ipbandroidview.integration

import com.progressterra.ipbandroidapi.iPBAndroidAPIModule
import com.progressterra.ipbandroidview.features.featuresModule
import com.progressterra.ipbandroidview.pages.pagesModule
import com.progressterra.ipbandroidview.processes.processesModule
import com.progressterra.ipbandroidview.shared.sharedModule
import com.progressterra.ipbandroidview.widgets.widgetsModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module
import org.koin.ksp.generated.module

@Module
@ComponentScan
class AnnotationModule


@Suppress("unused")
val ipbModule = module {
    includes(
        AnnotationModule().module,
        iPBAndroidAPIModule,
        pagesModule,
        widgetsModule,
        featuresModule,
        sharedModule,
        processesModule
    )
}