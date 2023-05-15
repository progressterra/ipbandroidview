package com.progressterra.ipbandroidview.di

import com.progressterra.ipbandroidview.ui.agreement.AgreementViewModel
import com.progressterra.ipbandroidview.ui.checklist.ChecklistViewModel
import com.progressterra.ipbandroidview.ui.confirmationcode.ConfirmationCodeViewModel
import com.progressterra.ipbandroidview.ui.documents.DocumentsViewModel
import com.progressterra.ipbandroidview.ui.mainhaccp.MainHaccpViewModel
import com.progressterra.ipbandroidview.ui.organizationaudits.OrganizationAuditsViewModel
import com.progressterra.ipbandroidview.ui.organizations.OrganizationsViewModel
import com.progressterra.ipbandroidview.ui.partner.PartnerViewModel
import com.progressterra.ipbandroidview.ui.photo.PhotoViewModel
import com.progressterra.ipbandroidview.ui.profile.ProfileViewModel
import com.progressterra.ipbandroidview.ui.profiledetails.ProfileDetailsViewModel
import com.progressterra.ipbandroidview.ui.signin.SignInViewModel
import com.progressterra.ipbandroidview.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { DocumentsViewModel(get()) }

    viewModel { SplashViewModel(get()) }

    viewModel { OrganizationsViewModel(get(), get()) }

    viewModel { SignInViewModel(get(), get(), get()) }


    viewModel { ConfirmationCodeViewModel(get(), get(), get()) }

    viewModel { OrganizationAuditsViewModel(get(), get()) }

    viewModel { AgreementViewModel(get()) }

    viewModel {
        ChecklistViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    viewModel { PhotoViewModel() }

    viewModel { ProfileViewModel(get(), get()) }

    viewModel { ProfileDetailsViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }

    viewModel { PartnerViewModel(get(), get()) }

    viewModel { MainHaccpViewModel(get(), get(), get()) }
}
