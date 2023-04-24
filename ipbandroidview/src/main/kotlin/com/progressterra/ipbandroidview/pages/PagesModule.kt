package com.progressterra.ipbandroidview.pages

import com.progressterra.ipbandroidview.pages.bonusesdetails.BonusesDetailsViewModel
import com.progressterra.ipbandroidview.pages.bonusesdetails.CancelUseBonusesUseCase
import com.progressterra.ipbandroidview.pages.bonusesdetails.UseBonusesUseCase
import com.progressterra.ipbandroidview.pages.cart.CartGoodsMapper
import com.progressterra.ipbandroidview.pages.cart.CartUseCase
import com.progressterra.ipbandroidview.pages.cart.CartViewModel
import com.progressterra.ipbandroidview.pages.catalog.CatalogUseCase
import com.progressterra.ipbandroidview.pages.catalog.CatalogViewModel
import com.progressterra.ipbandroidview.pages.confirmationcode.ConfirmationCodeViewModel
import com.progressterra.ipbandroidview.pages.confirmationcode.EndVerificationChannelUseCase
import com.progressterra.ipbandroidview.pages.delivery.DeliveryViewModel
import com.progressterra.ipbandroidview.pages.favorites.FavoriteGoodsUseCase
import com.progressterra.ipbandroidview.pages.favorites.FavoritesViewModel
import com.progressterra.ipbandroidview.pages.goodsdetails.GoodsDetailsMapper
import com.progressterra.ipbandroidview.pages.goodsdetails.GoodsDetailsUseCase
import com.progressterra.ipbandroidview.pages.goodsdetails.GoodsDetailsViewModel
import com.progressterra.ipbandroidview.pages.goodsdetails.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.pages.main.MainViewModel
import com.progressterra.ipbandroidview.pages.payment.ConfirmOrderUseCase
import com.progressterra.ipbandroidview.pages.payment.PaymentViewModel
import com.progressterra.ipbandroidview.pages.profile.ProfileViewModel
import com.progressterra.ipbandroidview.pages.profiledetails.ProfileDetailsViewModel
import com.progressterra.ipbandroidview.pages.signin.SignInViewModel
import com.progressterra.ipbandroidview.pages.signup.SignUpViewModel
import com.progressterra.ipbandroidview.pages.support.FetchChatUseCase
import com.progressterra.ipbandroidview.pages.support.UpdateFirebaseCloudMessagingTokenUseCase
import com.progressterra.ipbandroidview.pages.welcome.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pagesModule = module {

    viewModel { GoodsDetailsViewModel(get(), get()) }

    viewModel { WelcomeViewModel() }

    viewModel { ConfirmationCodeViewModel(get(), get()) }

    viewModel { SignInViewModel(get(), get()) }

    viewModel { SignUpViewModel(get(), get(), get()) }

    viewModel { MainViewModel(get(), get(), get(), get(), get()) }

    viewModel { PaymentViewModel(get(), get(), get()) }

    viewModel { DeliveryViewModel(get(), get()) }

    viewModel { BonusesDetailsViewModel(get(), get()) }

    viewModel { FavoritesViewModel(get(), get(), get()) }

    viewModel { ProfileViewModel(get(), get()) }

    viewModel { ProfileDetailsViewModel(get(), get(), get()) }

    single<FetchChatUseCase> { FetchChatUseCase.Base(get(), get()) }

    single<UpdateFirebaseCloudMessagingTokenUseCase> {
        UpdateFirebaseCloudMessagingTokenUseCase.Base(
            get(), get()
        )
    }

    single<EndVerificationChannelUseCase> {
//        EndVerificationChannelUseCase.Base(get(), get())
        EndVerificationChannelUseCase.Test()
    }

    single<CartGoodsMapper> { CartGoodsMapper.Base(get(), get(), get()) }

    single<CatalogUseCase> {
//        CatalogUseCase.Base(get(), get(), get(), get())
        CatalogUseCase.Test()
    }

    single<UseBonusesUseCase> { UseBonusesUseCase.Base(get(), get(), get()) }

    single<CancelUseBonusesUseCase> { CancelUseBonusesUseCase.Base(get(), get(), get()) }

    single<CartUseCase> {
//        CartUseCase.Base(get(), get(), get(), get(), get(), get())
        CartUseCase.Test()
    }

    single<GoodsDetailsUseCase> {
//        GoodsDetailsUseCase.Base(get(), get(), get(), get(), get(), get())
        GoodsDetailsUseCase.Test()
    }

    single<GoodsDetailsMapper> {
        GoodsDetailsMapper.Base(get(), get(), get())
    }

    single<FavoriteGoodsUseCase> {
//        FavoriteGoodsUseCase.Base(get(), get(), get(), get(), get())
        FavoriteGoodsUseCase.Test()
    }

    single<ModifyFavoriteUseCase> { ModifyFavoriteUseCase.Base(get(), get(), get()) }

    single<ConfirmOrderUseCase> {
//        ConfirmOrderUseCase.Base(get(), get(), get())
        ConfirmOrderUseCase.Test()
    }

    viewModel { CatalogViewModel(get(), get(), get(), get()) }

    viewModel { CartViewModel(get(), get(), get()) }
}