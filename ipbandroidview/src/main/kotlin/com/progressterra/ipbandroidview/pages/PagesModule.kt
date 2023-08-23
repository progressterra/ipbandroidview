package com.progressterra.ipbandroidview.pages

import com.progressterra.ipbandroidview.features.addresssuggestions.SuggestionsUseCase
import com.progressterra.ipbandroidview.pages.bankcarddetails.BankCardDetailsScreenViewModel
import com.progressterra.ipbandroidview.pages.bankcarddetails.FetchCardTemplateUseCase
import com.progressterra.ipbandroidview.pages.bankcards.BankCardsScreenViewModel
import com.progressterra.ipbandroidview.pages.bankcards.FetchUnconfirmedBankCardsUseCase
import com.progressterra.ipbandroidview.pages.bonusesdetails.BonusesDetailsViewModel
import com.progressterra.ipbandroidview.pages.bonusesdetails.CancelUseBonusesUseCase
import com.progressterra.ipbandroidview.pages.bonusesdetails.UseBonusesUseCase
import com.progressterra.ipbandroidview.pages.cart.CartUseCase
import com.progressterra.ipbandroidview.pages.cart.CartViewModel
import com.progressterra.ipbandroidview.pages.catalog.CatalogUseCase
import com.progressterra.ipbandroidview.pages.catalog.CatalogViewModel
import com.progressterra.ipbandroidview.pages.confirmationcode.ConfirmationCodeViewModel
import com.progressterra.ipbandroidview.pages.confirmationcode.EndVerificationChannelUseCase
import com.progressterra.ipbandroidview.pages.delivery.AddDeliveryToCartUseCase
import com.progressterra.ipbandroidview.pages.delivery.CommentUseCase
import com.progressterra.ipbandroidview.pages.delivery.DeliveryViewModel
import com.progressterra.ipbandroidview.pages.delivery.FetchShippingAddressUseCase
import com.progressterra.ipbandroidview.pages.documentdetails.DocumentDetailsViewModel
import com.progressterra.ipbandroidview.pages.documentdetails.FetchDocumentChatUseCase
import com.progressterra.ipbandroidview.pages.documentdetails.SaveDocumentsUseCase
import com.progressterra.ipbandroidview.pages.documents.DocumentsViewModel
import com.progressterra.ipbandroidview.pages.favorites.FavoriteGoodsUseCase
import com.progressterra.ipbandroidview.pages.favorites.FavoritesViewModel
import com.progressterra.ipbandroidview.pages.goodsdetails.GoodsDetailsUseCase
import com.progressterra.ipbandroidview.pages.goodsdetails.GoodsDetailsViewModel
import com.progressterra.ipbandroidview.pages.goodsdetails.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.pages.main.MainViewModel
import com.progressterra.ipbandroidview.pages.newwithdrawal.CreateNewWithdrawalUseCase
import com.progressterra.ipbandroidview.pages.newwithdrawal.NewWithdrawalScreenViewModel
import com.progressterra.ipbandroidview.pages.orderdetails.FetchOrderChatUseCase
import com.progressterra.ipbandroidview.pages.orderdetails.OrderDetailsScreenViewModel
import com.progressterra.ipbandroidview.pages.orderdetails.OrderDetailsUseCase
import com.progressterra.ipbandroidview.pages.orderlist.OrdersListViewModel
import com.progressterra.ipbandroidview.pages.orderlist.OrdersUseCase
import com.progressterra.ipbandroidview.pages.orderstatus.OrderStatusViewModel
import com.progressterra.ipbandroidview.pages.ordertracking.OrderTrackingScreenViewModel
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
import com.progressterra.ipbandroidview.pages.support.ChatsUseCase
import com.progressterra.ipbandroidview.pages.support.FetchChatsUseCase
import com.progressterra.ipbandroidview.pages.support.FetchMessagesUseCase
import com.progressterra.ipbandroidview.pages.support.SendMessageUseCase
import com.progressterra.ipbandroidview.pages.support.SupportScreenViewModel
import com.progressterra.ipbandroidview.pages.support.UpdateFirebaseCloudMessagingTokenUseCase
import com.progressterra.ipbandroidview.pages.wantthis.FetchWantThisUseCase
import com.progressterra.ipbandroidview.pages.wantthis.WantThisScreenViewModel
import com.progressterra.ipbandroidview.pages.wantthisrequests.WantThisRequestsUseCase
import com.progressterra.ipbandroidview.pages.wantthisrequests.WantThisRequestsViewModel
import com.progressterra.ipbandroidview.pages.welcome.WelcomeViewModel
import com.progressterra.ipbandroidview.pages.withdrawal.FetchWithdrawalTransactionsUseCase
import com.progressterra.ipbandroidview.pages.withdrawal.WithdrawalScreenViewModel
import com.progressterra.ipbandroidview.processes.payments.FetchConfirmedBankCardsUseCase
import com.progressterra.ipbandroidview.processes.payments.FetchMainCardIdUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pagesModule = module {

    viewModel { GoodsDetailsViewModel(get(), get(), get(), get(), get()) }

    viewModel { WelcomeViewModel() }

    viewModel { ConfirmationCodeViewModel(get(), get()) }

    viewModel { SignInViewModel(get(), get()) }

    viewModel { SignUpViewModel(get(), get()) }

    viewModel { MainViewModel(get(), get(), get(), get()) }

    viewModel { PaymentViewModel(get(), get(), get(), get(), get()) }

    viewModel { DeliveryViewModel(get(), get(), get(), get()) }

    viewModel { BonusesDetailsViewModel(get(), get()) }

    viewModel { FavoritesViewModel(get(), get(), get()) }

    viewModel { PhotoViewModel() }

    viewModel { ProfileViewModel(get(), get(), get()) }

    viewModel { ProfileDetailsViewModel(get(), get()) }

    viewModel { OrderDetailsScreenViewModel(get(), get(), get(), get()) }

    viewModel { OrderTrackingScreenViewModel() }

    single<OrdersUseCase> { OrdersUseCase.Base(get(), get()) }

    single<UpdateFirebaseCloudMessagingTokenUseCase> {
        UpdateFirebaseCloudMessagingTokenUseCase.Base(
            get(), get()
        )
    }

    single<OrderDetailsUseCase> {
        OrderDetailsUseCase.Base(get(), get(), get())
    }

    single<EndVerificationChannelUseCase> {
        EndVerificationChannelUseCase.Base(get(), get())
    }

    single<CatalogUseCase> {
        CatalogUseCase.Base(get(), get(), get())
    }

    single<UseBonusesUseCase> { UseBonusesUseCase.Base(get(), get()) }

    single<CancelUseBonusesUseCase> { CancelUseBonusesUseCase.Base(get(), get()) }

    single<CartUseCase> {
        CartUseCase.Base(get(), get(), get())
    }

    single<GoodsDetailsUseCase> {
        GoodsDetailsUseCase.Base(get(), get(), get(), get(), get())
    }

    single<FavoriteGoodsUseCase> {
        FavoriteGoodsUseCase.Base(get(), get(), get())
    }

    single<ModifyFavoriteUseCase> { ModifyFavoriteUseCase.Base(get(), get()) }

    single<ConfirmOrderUseCase> {
        ConfirmOrderUseCase.Base(get(), get(), get())
    }

    single<SaveDocumentsUseCase> { SaveDocumentsUseCase.Base(get(), get(), get(), get()) }

    viewModel { CatalogViewModel(get(), get(), get(), get()) }

    viewModel { CartViewModel(get(), get(), get()) }

    viewModel { DocumentsViewModel(get(), get(), get()) }

    viewModel { DocumentDetailsViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }

    single<DocumentsNotificationUseCase> { DocumentsNotificationUseCase.Base(get(), get()) }

    single<CommentUseCase> {
        CommentUseCase.Base(get(), get())
    }

    single<FetchWantThisUseCase> { FetchWantThisUseCase.Base(get()) }

    viewModel { OrdersListViewModel(get()) }

    single<FetchBonusSwitchUseCase> {
        FetchBonusSwitchUseCase.Base(get(), get())
    }

    single<FetchReceiptUseCase> {
        FetchReceiptUseCase.Base(get(), get())
    }

    viewModel { OrderStatusViewModel() }

    viewModel { WantThisRequestsViewModel(get(), get(), get()) }

    viewModel { WantThisScreenViewModel(get(), get(), get(), get(), get(), get()) }

    single<WantThisRequestsUseCase> {
        WantThisRequestsUseCase.Base(
            get(), get(), get(), get(), get()
        )
    }

    single<SuggestionsUseCase> {
        SuggestionsUseCase.Base(get())
    }

    single<FetchShippingAddressUseCase> {
        FetchShippingAddressUseCase.Base(get(), get())
    }

    single<AddDeliveryToCartUseCase> {
        AddDeliveryToCartUseCase.Base(get(), get(), get())
    }

    single<ChatsUseCase> {
        ChatsUseCase.Base(get(), get())
    }

    single<SendMessageUseCase> { SendMessageUseCase.Base(get(), get()) }

    single<FetchChatsUseCase> { FetchChatsUseCase.Base(get(), get(), get(), get()) }

    single<FetchMessagesUseCase> { FetchMessagesUseCase.Base(get(), get()) }

    single<FetchOrderChatUseCase> { FetchOrderChatUseCase.Base(get(), get(), get()) }

    single<FetchDocumentChatUseCase> { FetchDocumentChatUseCase.Base(get(), get(), get()) }

    viewModel { SupportScreenViewModel(get(), get(), get()) }

    viewModel { BankCardDetailsScreenViewModel(get(), get(), get(), get(), get(), get(), get()) }

    viewModel { BankCardsScreenViewModel(get(), get()) }

    single<FetchUnconfirmedBankCardsUseCase> {
        FetchUnconfirmedBankCardsUseCase.Base(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single<FetchConfirmedBankCardsUseCase> {
        FetchConfirmedBankCardsUseCase.Base(
            get(),
            get(),
            get()
        )
    }

    single<CreateNewWithdrawalUseCase> { CreateNewWithdrawalUseCase.Base(get(), get()) }

    single<FetchWithdrawalTransactionsUseCase> {
        FetchWithdrawalTransactionsUseCase.Base(
            get(),
            get()
        )
    }

    single<FetchCardTemplateUseCase> { FetchCardTemplateUseCase.Base(get()) }

    single<FetchMainCardIdUseCase> { FetchMainCardIdUseCase.Base(get(), get()) }

    viewModel { WithdrawalScreenViewModel(get(), get()) }

    viewModel { NewWithdrawalScreenViewModel(get(), get(), get()) }
}