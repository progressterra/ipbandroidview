package com.progressterra.ipbandroidview.pages

import com.progressterra.ipbandroidview.pages.bonuses.BonusesViewModel
import com.progressterra.ipbandroidview.pages.confirmationcode.ConfirmationCodeViewModel
import com.progressterra.ipbandroidview.pages.delivery.DeliveryViewModel
import com.progressterra.ipbandroidview.pages.payment.PaymentViewModel
import com.progressterra.ipbandroidview.pages.proshkamain.ProshkaMainViewModel
import com.progressterra.ipbandroidview.pages.signin.SignInViewModel
import com.progressterra.ipbandroidview.pages.signup.SignUpViewModel
import com.progressterra.ipbandroidview.pages.welcome.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pagesModule = module {

    viewModel { WelcomeViewModel() }

    viewModel { ConfirmationCodeViewModel(get(), get()) }

    viewModel { SignInViewModel(get(), get()) }

    viewModel { SignUpViewModel() }

    viewModel { ProshkaMainViewModel(get(), get(), get(), get(), get()) }

    viewModel { PaymentViewModel(get()) }

    viewModel { DeliveryViewModel(get()) }

    viewModel { BonusesViewModel(get(), get()) }
}