package com.progressterra.ipbandroidview.di

import com.progressterra.ipbandroidapi.di.iPBAndroidAPIModule
import com.progressterra.ipbandroidview.domain.EndVerificationChannelUseCase
import com.progressterra.ipbandroidview.domain.StartVerificationChannelUseCase
import com.progressterra.ipbandroidview.domain.UpdateFirebaseCloudMessagingTokenUseCase
import com.progressterra.ipbandroidview.domain.UpdatePersonalInfoUseCase
import org.koin.dsl.module

val iPBAndroidViewModule = module {

    includes(iPBAndroidAPIModule)

    single<EndVerificationChannelUseCase> {
        EndVerificationChannelUseCase.Base(get())
    }

    single<StartVerificationChannelUseCase> {
        StartVerificationChannelUseCase.Base(get())
    }

    single<UpdatePersonalInfoUseCase> {
        UpdatePersonalInfoUseCase.Base(get(), get())
    }

    single<UpdateFirebaseCloudMessagingTokenUseCase> {
        UpdateFirebaseCloudMessagingTokenUseCase.Base(get(), get())
    }
}