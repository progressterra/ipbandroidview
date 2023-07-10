package com.progressterra.ipbandroidview.pages

import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.features.addresssuggestions.SuggestionsUseCase
import com.progressterra.ipbandroidview.pages.bonusesdetails.BonusesDetailsViewModel
import com.progressterra.ipbandroidview.pages.bonusesdetails.CancelUseBonusesUseCase
import com.progressterra.ipbandroidview.pages.bonusesdetails.UseBonusesUseCase
import com.progressterra.ipbandroidview.pages.cart.CartUseCase
import com.progressterra.ipbandroidview.pages.cart.CartViewModel
import com.progressterra.ipbandroidview.pages.catalog.CatalogUseCase
import com.progressterra.ipbandroidview.pages.catalog.CatalogViewModel
import com.progressterra.ipbandroidview.pages.confirmationcode.ConfirmationCodeViewModel
import com.progressterra.ipbandroidview.pages.confirmationcode.EndVerificationChannelUseCase
import com.progressterra.ipbandroidview.pages.delivery.CommentUseCase
import com.progressterra.ipbandroidview.pages.delivery.DeliveryViewModel
import com.progressterra.ipbandroidview.pages.documentdetails.DocumentDetailsViewModel
import com.progressterra.ipbandroidview.pages.documentdetails.SaveDocumentsUseCase
import com.progressterra.ipbandroidview.pages.documentdetails.ValidationUseCase
import com.progressterra.ipbandroidview.pages.documents.DocumentsViewModel
import com.progressterra.ipbandroidview.pages.favorites.FavoriteGoodsUseCase
import com.progressterra.ipbandroidview.pages.favorites.FavoritesViewModel
import com.progressterra.ipbandroidview.pages.goodsdetails.GoodsDetailsUseCase
import com.progressterra.ipbandroidview.pages.goodsdetails.GoodsDetailsViewModel
import com.progressterra.ipbandroidview.pages.goodsdetails.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.pages.main.MainViewModel
import com.progressterra.ipbandroidview.pages.orderlist.OrdersListViewModel
import com.progressterra.ipbandroidview.pages.orderlist.OrdersUseCase
import com.progressterra.ipbandroidview.pages.orders.OrdersViewModel
import com.progressterra.ipbandroidview.pages.orderstatus.OrderStatusViewModel
import com.progressterra.ipbandroidview.pages.payment.ConfirmOrderUseCase
import com.progressterra.ipbandroidview.pages.payment.FetchBonusSwitchUseCase
import com.progressterra.ipbandroidview.pages.payment.FetchReceiptUseCase
import com.progressterra.ipbandroidview.pages.payment.PaymentViewModel
import com.progressterra.ipbandroidview.pages.photo.PhotoViewModel
import com.progressterra.ipbandroidview.pages.profile.DocumentsNotificationUseCase
import com.progressterra.ipbandroidview.pages.profile.ProfileViewModel
import com.progressterra.ipbandroidview.pages.profiledetails.ProfileDetailsViewModel
import com.progressterra.ipbandroidview.pages.signin.SignInViewModel
import com.progressterra.ipbandroidview.pages.signup.SignUpViewModel
import com.progressterra.ipbandroidview.pages.support.FetchChatUseCase
import com.progressterra.ipbandroidview.pages.support.UpdateFirebaseCloudMessagingTokenUseCase
import com.progressterra.ipbandroidview.pages.wantthis.FetchWantThisUseCase
import com.progressterra.ipbandroidview.pages.wantthis.WantThisScreenViewModel
import com.progressterra.ipbandroidview.pages.wantthisrequests.WantThisRequestsUseCase
import com.progressterra.ipbandroidview.pages.wantthisrequests.WantThisRequestsViewModel
import com.progressterra.ipbandroidview.pages.welcome.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pagesModule = module {

    viewModel { GoodsDetailsViewModel(get(), get(), get(), get(), get()) }

    viewModel { WelcomeViewModel() }

    viewModel { ConfirmationCodeViewModel(get(), get()) }

    viewModel { SignInViewModel(get(), get()) }

    viewModel { SignUpViewModel(get(), get(), get()) }

    viewModel { MainViewModel(get(), get(), get(), get(), get()) }

    viewModel { PaymentViewModel(get(), get(), get(), get(), get()) }

    viewModel { DeliveryViewModel(get(), get(), get(), get()) }

    viewModel { BonusesDetailsViewModel(get(), get()) }

    viewModel { FavoritesViewModel(get(), get(), get()) }

    viewModel { PhotoViewModel() }

    viewModel { ProfileViewModel(get(), get(), get()) }

    viewModel { ProfileDetailsViewModel(get(), get(), get()) }

    viewModel { OrdersViewModel() }

    single<OrdersUseCase> { OrdersUseCase.Base(get(), get(), get(), get(), get()) }

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
            CartUseCase.Base(get(), get(), get(), get())
        }
    }

    single {
        if (IpbAndroidViewSettings.TEST_MODE) {
            GoodsDetailsUseCase.Test()
        } else {
            GoodsDetailsUseCase.Base(get(), get(), get(), get(), get(), get())
        }
    }

    single {
        if (IpbAndroidViewSettings.TEST_MODE) {
            FavoriteGoodsUseCase.Test()
        } else {
            FavoriteGoodsUseCase.Base(get(), get(), get(), get())
        }
    }

    single<ModifyFavoriteUseCase> { ModifyFavoriteUseCase.Base(get(), get(), get()) }

    single<ConfirmOrderUseCase> {
        ConfirmOrderUseCase.Base(get(), get(), get(), get())
    }

    single<SaveDocumentsUseCase> { SaveDocumentsUseCase.Base(get(), get(), get(), get(), get()) }

    viewModel { CatalogViewModel(get(), get(), get(), get()) }

    viewModel { CartViewModel(get(), get(), get()) }

    viewModel { DocumentsViewModel(get(), get(), get()) }

    viewModel { DocumentDetailsViewModel(get(), get(), get(), get(), get()) }

    single<ValidationUseCase> { ValidationUseCase.Base() }

    single<DocumentsNotificationUseCase> { DocumentsNotificationUseCase.Base(get(), get(), get()) }

    single<CommentUseCase> {
        CommentUseCase.Base(get(), get(), get())
    }

    single<FetchWantThisUseCase> { FetchWantThisUseCase.Base(get(), get(), get(), get(), get()) }

    viewModel { OrdersListViewModel(get()) }

    single<FetchBonusSwitchUseCase> {
        FetchBonusSwitchUseCase.Base(get(), get(), get())
    }

    single<FetchReceiptUseCase> {
        FetchReceiptUseCase.Base(get(), get(), get())
    }

    viewModel { OrderStatusViewModel() }

    viewModel { WantThisRequestsViewModel(get(), get(), get()) }

    viewModel { WantThisScreenViewModel(get(), get(), get(), get(), get()) }

    single<WantThisRequestsUseCase> {
        WantThisRequestsUseCase.Base(
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single<SuggestionsUseCase> {
        SuggestionsUseCase.Base(get())
    }
}