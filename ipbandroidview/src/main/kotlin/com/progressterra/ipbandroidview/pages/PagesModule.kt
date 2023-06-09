package com.progressterra.ipbandroidview.pages

import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.pages.bonusesdetails.BonusesDetailsViewModel
import com.progressterra.ipbandroidview.pages.bonusesdetails.CancelUseBonusesUseCase
import com.progressterra.ipbandroidview.pages.bonusesdetails.UseBonusesUseCase
import com.progressterra.ipbandroidview.pages.cart.CartUseCase
import com.progressterra.ipbandroidview.pages.cart.CartViewModel
import com.progressterra.ipbandroidview.pages.catalog.CatalogUseCase
import com.progressterra.ipbandroidview.pages.catalog.CatalogViewModel
import com.progressterra.ipbandroidview.pages.confirmationcode.ConfirmationCodeViewModel
import com.progressterra.ipbandroidview.pages.confirmationcode.EndVerificationChannelUseCase
import com.progressterra.ipbandroidview.pages.delivery.DeliveryViewModel
import com.progressterra.ipbandroidview.pages.documentdetails.DocumentDetailsViewModel
import com.progressterra.ipbandroidview.pages.documentdetails.SaveDocumentsUseCase
import com.progressterra.ipbandroidview.pages.documents.DocumentsViewModel
import com.progressterra.ipbandroidview.pages.documents.OpenDocumentUseCase
import com.progressterra.ipbandroidview.pages.favorites.FavoriteGoodsUseCase
import com.progressterra.ipbandroidview.pages.favorites.FavoritesViewModel
import com.progressterra.ipbandroidview.pages.goodsdetails.GoodsDetailsUseCase
import com.progressterra.ipbandroidview.pages.goodsdetails.GoodsDetailsViewModel
import com.progressterra.ipbandroidview.pages.goodsdetails.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.pages.main.MainViewModel
import com.progressterra.ipbandroidview.pages.orders.OrdersUseCase
import com.progressterra.ipbandroidview.pages.orders.OrdersViewModel
import com.progressterra.ipbandroidview.pages.orders.StatusOrderMapper
import com.progressterra.ipbandroidview.pages.payment.ConfirmOrderUseCase
import com.progressterra.ipbandroidview.pages.payment.PaymentViewModel
import com.progressterra.ipbandroidview.pages.photo.PhotoViewModel
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

    viewModel { DeliveryViewModel(get(), get(), get()) }

    viewModel { BonusesDetailsViewModel(get(), get()) }

    viewModel { FavoritesViewModel(get(), get(), get()) }

    viewModel { PhotoViewModel() }

    viewModel { ProfileViewModel(get(), get()) }

    viewModel { ProfileDetailsViewModel(get(), get(), get()) }

    viewModel { OrdersViewModel(get()) }

    single<StatusOrderMapper> { StatusOrderMapper.Base(get()) }

    single<OrdersUseCase> { OrdersUseCase.Base(get(), get(), get(), get(), get(), get(), get()) }

    single<FetchChatUseCase> { FetchChatUseCase.Base(get(), get()) }

    single<UpdateFirebaseCloudMessagingTokenUseCase> {
        UpdateFirebaseCloudMessagingTokenUseCase.Base(
            get(), get()
        )
    }

    single {
        if (IpbAndroidViewSettings.TEST_MODE) {
            EndVerificationChannelUseCase.Test()
        } else {
            EndVerificationChannelUseCase.Base(get(), get())
        }
    }

    single {
        if (IpbAndroidViewSettings.TEST_MODE) {
            CatalogUseCase.Test()
        } else {
            CatalogUseCase.Base(get(), get(), get(), get())
        }
    }

    single<UseBonusesUseCase> { UseBonusesUseCase.Base(get(), get(), get()) }

    single<CancelUseBonusesUseCase> { CancelUseBonusesUseCase.Base(get(), get(), get()) }

    single {
        if (IpbAndroidViewSettings.TEST_MODE) {
            CartUseCase.Test()
        } else {
            CartUseCase.Base(get(), get(), get(), get(), get())
        }
    }

    single {
        if (IpbAndroidViewSettings.TEST_MODE) {
            GoodsDetailsUseCase.Test()
        } else {
            GoodsDetailsUseCase.Base(get(), get(), get(), get(), get(), get(), get())
        }
    }

    single {
        if (IpbAndroidViewSettings.TEST_MODE) {
            FavoriteGoodsUseCase.Test()
        } else {
            FavoriteGoodsUseCase.Base(get(), get(), get(), get(), get())
        }
    }

    single<ModifyFavoriteUseCase> { ModifyFavoriteUseCase.Base(get(), get(), get()) }

    single {
        if (IpbAndroidViewSettings.TEST_MODE) {
            ConfirmOrderUseCase.Test()
        } else {
            ConfirmOrderUseCase.Base(get(), get(), get())
        }
    }

    single<OpenDocumentUseCase> { OpenDocumentUseCase.Base() }

    single<SaveDocumentsUseCase> { SaveDocumentsUseCase.Base(get(), get(), get(), get(), get()) }

    viewModel { CatalogViewModel(get(), get(), get(), get()) }

    viewModel { CartViewModel(get(), get(), get()) }

    viewModel { DocumentsViewModel(get(), get(), get(), get()) }

    viewModel { DocumentDetailsViewModel(get(), get(), get(), get()) }
}