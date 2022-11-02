package com.progressterra.ipbandroidview.di

import com.progressterra.ipbandroidview.ui.checklist.ChecklistViewModel
import com.progressterra.ipbandroidview.ui.city.CityViewModel
import com.progressterra.ipbandroidview.ui.confirmationcode.ConfirmationCodeViewModel
import com.progressterra.ipbandroidview.ui.documents.DocumentsViewModel
import com.progressterra.ipbandroidview.ui.main.MainViewModel
import com.progressterra.ipbandroidview.ui.organizationaudits.OrganizationAuditsViewModel
import com.progressterra.ipbandroidview.ui.organizations.OrganizationsViewModel
import com.progressterra.ipbandroidview.ui.photo.PhotoViewModel
import com.progressterra.ipbandroidview.ui.profile.ProfileViewModel
import com.progressterra.ipbandroidview.ui.profiledetails.ProfileDetailsViewModel
import com.progressterra.ipbandroidview.ui.signin.SignInViewModel
import com.progressterra.ipbandroidview.ui.signup.SignUpViewModel
import com.progressterra.ipbandroidview.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelsModule = module {

    viewModel {
        MainViewModel(get())
    }

    viewModel {
        DocumentsViewModel(get(), get())
    }

    viewModel {
        SplashViewModel()
    }

    viewModel {
        OrganizationsViewModel(get())
    }

    viewModel {
        SignInViewModel(get())
    }

    viewModel {
        SignUpViewModel(get())
    }

    viewModel {
        ConfirmationCodeViewModel(get(), get())
    }

    viewModel {
        OrganizationAuditsViewModel(get(), get(), get())
    }

    viewModel {
        CityViewModel(get(), get(), get(), get())
    }

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
            get()
        )
    }

    viewModel {
        PhotoViewModel()
    }

    viewModel {
        ProfileViewModel()
    }

    viewModel {
        ProfileDetailsViewModel(get())
    }
}