package com.progressterra.ipbandroidview.pages

import com.progressterra.ipbandroidview.pages.bonuses.BonusesViewModel
import com.progressterra.ipbandroidview.pages.bonuses.CancelUseBonusesUseCase
import com.progressterra.ipbandroidview.pages.bonuses.UseBonusesUseCase
import com.progressterra.ipbandroidview.pages.cart.CartUseCase
import com.progressterra.ipbandroidview.pages.confirmationcode.ConfirmationCodeViewModel
import com.progressterra.ipbandroidview.pages.confirmationcode.EndVerificationChannelUseCase
import com.progressterra.ipbandroidview.pages.delivery.DeliveryViewModel
import com.progressterra.ipbandroidview.pages.favorites.FavoriteGoodsUseCase
import com.progressterra.ipbandroidview.pages.favorites.FavoritesViewModel
import com.progressterra.ipbandroidview.pages.goodsdetails.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.pages.payment.ConfirmOrderUseCase
import com.progressterra.ipbandroidview.pages.payment.PaymentViewModel
import com.progressterra.ipbandroidview.pages.proshkamain.ProshkaMainViewModel
import com.progressterra.ipbandroidview.pages.signin.SignInViewModel
import com.progressterra.ipbandroidview.pages.signup.SignUpViewModel
import com.progressterra.ipbandroidview.pages.support.FetchChatUseCase
import com.progressterra.ipbandroidview.pages.support.UpdateFirebaseCloudMessagingTokenUseCase
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

    viewModel { FavoritesViewModel(get(), get(), get()) }

    single<FetchChatUseCase> { FetchChatUseCase.Base(get(), get(), get()) }

    single<UpdateFirebaseCloudMessagingTokenUseCase> {
        UpdateFirebaseCloudMessagingTokenUseCase.Base(
            get(),
            get()
        )
    }

    single<EndVerificationChannelUseCase> { EndVerificationChannelUseCase.Base(get(), get()) }

    single<UseBonusesUseCase> { UseBonusesUseCase.Base(get(), get(), get()) }

    single<CancelUseBonusesUseCase> { CancelUseBonusesUseCase.Base(get(), get(), get()) }

    single<CartUseCase> { CartUseCase.Base(get(), get(), get(), get(), get(), get()) }

    single<FavoriteGoodsUseCase> { FavoriteGoodsUseCase.Base(get(), get(), get(), get(), get()) }

    single<ModifyFavoriteUseCase> { ModifyFavoriteUseCase.Base(get(), get(), get()) }

    single<ConfirmOrderUseCase> { ConfirmOrderUseCase.Base(get(), get(), get()) }
}