package com.progressterra.ipbandroidview.di

import android.content.Context
import com.progressterra.ipbandroidapi.api.scrm.SCRMCacheDataSource
import com.progressterra.ipbandroidapi.api.scrm.SCRMCloudDataSource
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMService
import com.progressterra.ipbandroidapi.exception.HandleException
import com.progressterra.ipbandroidapi.remotedata.NetworkServiceImpl
import com.progressterra.ipbandroidapi.remotedata.NetworkSettings
import com.progressterra.ipbandroidview.ui.login.login.LoginViewModel
import com.progressterra.ipbandroidview.ui.login.settings.LoginFlowSettings
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val UI_LIBRARY_SHARED_PREFERENCES = "ui library shared preferences"

//TODO NetworkServiceImpl???
val iProBonusUIModule = module {
    single<SCRMRepository> {
        SCRMRepository.Base(SCRMCloudDataSource.Base(NetworkServiceImpl().createService(SCRMService::class.java, NetworkSettings.SCRM_URL), HandleException.Base()), SCRMCacheDataSource.Base(androidContext().getSharedPreferences(
            UI_LIBRARY_SHARED_PREFERENCES, Context.MODE_PRIVATE)))
    }

    viewModel { (selectedCountry: String, loginFlowSettings: LoginFlowSettings) ->
        LoginViewModel(selectedCountry, loginFlowSettings, get())
    }
}