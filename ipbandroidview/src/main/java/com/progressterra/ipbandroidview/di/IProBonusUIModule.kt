package com.progressterra.ipbandroidview.di

import android.content.Context
import com.progressterra.ipbandroidapi.api.URL
import com.progressterra.ipbandroidapi.api.ibonus.IBonusCloudDataSource
import com.progressterra.ipbandroidapi.api.ibonus.IBonusRepository
import com.progressterra.ipbandroidapi.api.ibonus.IBonusService
import com.progressterra.ipbandroidapi.api.iecommerscoreapi.IECommersCore
import com.progressterra.ipbandroidapi.api.ipbambassador.IPBAmbassador
import com.progressterra.ipbandroidapi.api.ipbmediadatacore.IpbMediaDataCore
import com.progressterra.ipbandroidapi.api.ipbpromocodeapi.IPBPromoCode
import com.progressterra.ipbandroidapi.api.scrm.SCRMCacheDataSource
import com.progressterra.ipbandroidapi.api.scrm.SCRMCloudDataSource
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMService
import com.progressterra.ipbandroidapi.core.NetworkService
import com.progressterra.ipbandroidapi.exception.HandleException
import com.progressterra.ipbandroidview.ValuesUI.SHARED_PREFERENCES
import com.progressterra.ipbandroidview.data.AmbassadorRepository
import com.progressterra.ipbandroidview.data.BaseRepository
import com.progressterra.ipbandroidview.data.CommonRepository
import com.progressterra.ipbandroidview.data.IRepository
import com.progressterra.ipbandroidview.ui.addresses.AddNewAddressViewModel
import com.progressterra.ipbandroidview.ui.addresses.ListOfAddressesViewModel
import com.progressterra.ipbandroidview.ui.bonuses_banner.BonusesBannerViewModel
import com.progressterra.ipbandroidview.ui.login.confirm.ConfirmViewModel
import com.progressterra.ipbandroidview.ui.login.login.LoginViewModel
import com.progressterra.ipbandroidview.ui.login.settings.LoginFlowSettings
import com.progressterra.ipbandroidview.ui.promocode.PromoCodeViewModel
import com.progressterra.ipbandroidview.ui.user_inviting.UserInviteInfoViewModel
import com.progressterra.ipbandroidview.usecases.scrm.ConfirmSMSCodeUseCase
import com.progressterra.ipbandroidview.usecases.scrm.GetClientCityUseCase
import com.progressterra.ipbandroidview.usecases.scrm.StartSMSAuthUseCase
import com.progressterra.ipbandroidview.usecases.scrm.UpdateClientInfoUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.binds
import org.koin.dsl.module

val iProBonusUIModule = module {

    single<SCRMRepository> {
        SCRMRepository.Base(
            SCRMCloudDataSource.Base(
                NetworkService.Base().createService(
                    SCRMService::class.java,
                    URL.SCRM_URL
                ), HandleException.Base()
            ), SCRMCacheDataSource.Base(
                androidContext().getSharedPreferences(
                    SHARED_PREFERENCES, Context.MODE_PRIVATE
                )
            )
        )
    }

    single<IBonusRepository> {
        IBonusRepository.Base(
            IBonusCloudDataSource.Base(
                NetworkService.Base().createService(
                    IBonusService::class.java,
                    URL.IBONUS_URL
                ), HandleException.Base()
            )
        )
    }

    single { AmbassadorRepository(get(), get(), get()) }

    single { IPBAmbassador.Ambassador() }

    single { IpbMediaDataCore.EntityMobile() }

    single<IRepository> { BaseRepository(get()) }

    single { IPBPromoCode.PromoCodeUse() }

    single { IECommersCore.Catalog() }

    single { CommonRepository(get(), get(), get(), get()) }.binds(
        arrayOf(
            IRepository.PromoCode::class, IRepository.Bonuses::class,
            IRepository.Catalog::class
        )
    )

    single { IPBPromoCode.PromoCodeUse() }

    single { IECommersCore.Catalog() }

    viewModel { (selectedCountry: String, loginFlowSettings: LoginFlowSettings) ->
        LoginViewModel(selectedCountry, loginFlowSettings, get())
    }

    viewModel {
        BonusesBannerViewModel(get())
    }

    viewModel {
        PromoCodeViewModel(get())
    }

    viewModel {
        AddNewAddressViewModel(get(), get())
    }

    viewModel {
        ListOfAddressesViewModel(get(), get())
    }

    viewModel { (phoneNumber: String, loginFlowSettings: LoginFlowSettings) ->
        ConfirmViewModel(phoneNumber, loginFlowSettings, get(), get())
    }

    viewModel {
        UserInviteInfoViewModel(get())
    }

    factory {
        UpdateClientInfoUseCase.Base(get())
    }

    factory {
        ConfirmSMSCodeUseCase.Base(get())
    }

    factory {
        StartSMSAuthUseCase.Base(get())
    }

    factory {
        GetClientCityUseCase.Base(get(), get())
    }
}