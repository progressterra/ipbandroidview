package com.progressterra.ipbandroidview.pages

import com.progressterra.ipbandroidview.pages.avatarpicker.AvatarPickerScreenViewModel
import com.progressterra.ipbandroidview.pages.bankcarddetails.BankCardDetailsScreenViewModel
import com.progressterra.ipbandroidview.pages.bankcards.BankCardsScreenViewModel
import com.progressterra.ipbandroidview.pages.bonusesdetails.BonusesDetailsScreenViewModel
import com.progressterra.ipbandroidview.pages.cart.CartScreenViewModel
import com.progressterra.ipbandroidview.pages.catalog.CatalogScreenViewModel
import com.progressterra.ipbandroidview.pages.chat.ChatScreenViewModel
import com.progressterra.ipbandroidview.pages.chats.ChatsScreenViewModel
import com.progressterra.ipbandroidview.pages.confirmationcode.ConfirmationCodeScreenViewModel
import com.progressterra.ipbandroidview.pages.connections.ConnectionsScreenViewModel
import com.progressterra.ipbandroidview.pages.datingmain.DatingMainScreenViewModel
import com.progressterra.ipbandroidview.pages.datingprofile.DatingProfileScreenViewModel
import com.progressterra.ipbandroidview.pages.delivery.DeliveryScreenViewModel
import com.progressterra.ipbandroidview.pages.documentdetails.DocumentDetailsViewModel
import com.progressterra.ipbandroidview.pages.documents.DocumentsScreenViewModel
import com.progressterra.ipbandroidview.pages.favorites.FavoritesScreenViewModel
import com.progressterra.ipbandroidview.pages.goodsdetails.GoodsDetailsScreenViewModel
import com.progressterra.ipbandroidview.pages.info.InfoScreenViewModel
import com.progressterra.ipbandroidview.pages.interests.InterestsScreenViewModel
import com.progressterra.ipbandroidview.pages.locationpermission.LocationPermissionScreenViewModel
import com.progressterra.ipbandroidview.pages.main.MainScreenViewModel
import com.progressterra.ipbandroidview.pages.newwithdrawal.NewWithdrawalScreenViewModel
import com.progressterra.ipbandroidview.pages.occupacion.OccupationScreenViewModel
import com.progressterra.ipbandroidview.pages.orderdetails.OrderDetailsScreenViewModel
import com.progressterra.ipbandroidview.pages.orders.OrdersScreenViewModel
import com.progressterra.ipbandroidview.pages.orderstatus.OrderStatusScreenViewModel
import com.progressterra.ipbandroidview.pages.ordertracking.OrderTrackingScreenViewModel
import com.progressterra.ipbandroidview.pages.payment.PaymentScreenViewModel
import com.progressterra.ipbandroidview.pages.pfppicker.PfpPickerScreenViewModel
import com.progressterra.ipbandroidview.pages.photo.PhotoScreenViewModel
import com.progressterra.ipbandroidview.pages.profile.ProfileScreenViewModel
import com.progressterra.ipbandroidview.pages.profiledetails.ProfileDetailsScreenViewModel
import com.progressterra.ipbandroidview.pages.signin.SignInScreenViewModel
import com.progressterra.ipbandroidview.pages.signup.SignUpScreenViewModel
import com.progressterra.ipbandroidview.pages.support.SupportScreenViewModel
import com.progressterra.ipbandroidview.pages.wantthis.WantThisScreenViewModel
import com.progressterra.ipbandroidview.pages.wantthisdetails.WantThisDetailsScreenViewModel
import com.progressterra.ipbandroidview.pages.wantthisrequests.WantThisRequestsScreenViewModel
import com.progressterra.ipbandroidview.pages.welcome.WelcomeScreenViewModel
import com.progressterra.ipbandroidview.pages.withdrawal.WithdrawalScreenViewModel
import com.progressterra.ipbandroidview.processes.AddDeliveryToCartUseCase
import com.progressterra.ipbandroidview.processes.CancelUseBonusesUseCase
import com.progressterra.ipbandroidview.processes.CatalogUseCase
import com.progressterra.ipbandroidview.processes.ChatsUseCase
import com.progressterra.ipbandroidview.processes.CommentUseCase
import com.progressterra.ipbandroidview.processes.ConfirmOrderUseCase
import com.progressterra.ipbandroidview.processes.CreateNewWithdrawalUseCase
import com.progressterra.ipbandroidview.processes.DocumentsNotificationUseCase
import com.progressterra.ipbandroidview.processes.EndVerificationChannelUseCase
import com.progressterra.ipbandroidview.processes.FavoriteGoodsUseCase
import com.progressterra.ipbandroidview.processes.FetchBonusSwitchUseCase
import com.progressterra.ipbandroidview.processes.FetchCardTemplateUseCase
import com.progressterra.ipbandroidview.processes.FetchCartUseCase
import com.progressterra.ipbandroidview.processes.FetchChatsUseCase
import com.progressterra.ipbandroidview.processes.FetchDocumentChatUseCase
import com.progressterra.ipbandroidview.processes.FetchMessagesUseCase
import com.progressterra.ipbandroidview.processes.FetchOrderChatUseCase
import com.progressterra.ipbandroidview.processes.FetchReceiptUseCase
import com.progressterra.ipbandroidview.processes.FetchShippingAddressUseCase
import com.progressterra.ipbandroidview.processes.FetchUnconfirmedBankCardsUseCase
import com.progressterra.ipbandroidview.processes.FetchWantThisDetailsChatUseCase
import com.progressterra.ipbandroidview.processes.FetchWantThisUseCase
import com.progressterra.ipbandroidview.processes.FetchWithdrawalTransactionsUseCase
import com.progressterra.ipbandroidview.processes.GoodsDetailsUseCase
import com.progressterra.ipbandroidview.processes.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.processes.OrderDetailsUseCase
import com.progressterra.ipbandroidview.processes.OrdersUseCase
import com.progressterra.ipbandroidview.processes.SaveDocumentsUseCase
import com.progressterra.ipbandroidview.processes.SendMessageUseCase
import com.progressterra.ipbandroidview.processes.SuggestionsUseCase
import com.progressterra.ipbandroidview.processes.UseBonusesUseCase
import com.progressterra.ipbandroidview.processes.WantThisRequestsUseCase
import com.progressterra.ipbandroidview.processes.payments.FetchConfirmedBankCardsUseCase
import com.progressterra.ipbandroidview.processes.payments.FetchMainCardIdUseCase
import com.progressterra.ipbandroidview.widgets.peoplenearby.PeopleNearbyScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pagesModule = module {

    viewModel { GoodsDetailsScreenViewModel(get(), get(), get(), get(), get()) }

    viewModel { WelcomeScreenViewModel() }

    viewModel { ConfirmationCodeScreenViewModel(get(), get()) }

    viewModel { SignInScreenViewModel(get(), get()) }

    viewModel { SignUpScreenViewModel(get(), get()) }

    viewModel { MainScreenViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }

    viewModel { PaymentScreenViewModel(get(), get(), get(), get(), get()) }

    viewModel { DeliveryScreenViewModel(get(), get(), get(), get()) }

    viewModel { BonusesDetailsScreenViewModel(get(), get()) }

    viewModel { FavoritesScreenViewModel(get(), get(), get()) }

    viewModel { PhotoScreenViewModel() }

    viewModel { ProfileScreenViewModel(get(), get(), get(), get()) }

    viewModel { ProfileDetailsScreenViewModel(get(), get(), get(), get(), get(), get()) }

    viewModel { OrderDetailsScreenViewModel(get(), get(), get(), get()) }

    viewModel { OrderTrackingScreenViewModel() }

    viewModel {
        DatingMainScreenViewModel(
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
        DatingProfileScreenViewModel(
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

    viewModel { AvatarPickerScreenViewModel() }

    viewModel { PfpPickerScreenViewModel(get(), get(), get()) }

    viewModel { InfoScreenViewModel(get()) }

    viewModel { InterestsScreenViewModel(get(), get()) }

    viewModel { OccupationScreenViewModel(get(), get(), get()) }

    viewModel { ChatsScreenViewModel(get()) }

    viewModel { PeopleNearbyScreenViewModel(get()) }

    single<OrdersUseCase> { OrdersUseCase.Base(get(), get()) }

    single<OrderDetailsUseCase> {
        OrderDetailsUseCase.Base(get(), get(), get(), get(), get())
    }

    single<EndVerificationChannelUseCase> {
        EndVerificationChannelUseCase.Base(get(), get(), get(), get(), get())
    }

    single<CatalogUseCase> {
        CatalogUseCase.Base(get(), get(), get(), get())
    }

    single<UseBonusesUseCase> { UseBonusesUseCase.Base(get(), get(), get(), get()) }

    single<CancelUseBonusesUseCase> { CancelUseBonusesUseCase.Base(get(), get(), get(), get()) }

    single<FetchCartUseCase> {
        FetchCartUseCase.Base(get(), get(), get(), get(), get())
    }

    single<GoodsDetailsUseCase> {
        GoodsDetailsUseCase.Base(get(), get(), get(), get(), get(), get())
    }

    single<FavoriteGoodsUseCase> {
        FavoriteGoodsUseCase.Base()
    }

    single<ModifyFavoriteUseCase> { ModifyFavoriteUseCase.Base() }

    single<ConfirmOrderUseCase> {
        ConfirmOrderUseCase.Base(get(), get(), get(), get(), get())
    }

    single<SaveDocumentsUseCase> { SaveDocumentsUseCase.Base(get(), get(), get(), get(), get(), get()) }

    viewModel { CatalogScreenViewModel(get(), get(), get(), get()) }

    viewModel { CartScreenViewModel(get(), get(), get()) }

    viewModel { DocumentsScreenViewModel(get(), get(), get()) }

    viewModel { DocumentDetailsViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }

    single<DocumentsNotificationUseCase> { DocumentsNotificationUseCase.Base(get(), get(), get(), get()) }

    single<CommentUseCase> {
        CommentUseCase.Base(get(), get(), get(), get())
    }

    single<FetchWantThisUseCase> { FetchWantThisUseCase.Base(get(), get(), get()) }

    viewModel { OrdersScreenViewModel(get()) }

    single<FetchBonusSwitchUseCase> {
        FetchBonusSwitchUseCase.Base(get(), get())
    }

    single<FetchReceiptUseCase> {
        FetchReceiptUseCase.Base(get(), get(), get(), get())
    }

    viewModel { OrderStatusScreenViewModel() }

    viewModel { WantThisRequestsScreenViewModel(get(), get(), get()) }

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
        FetchShippingAddressUseCase.Base(get(), get(), get(), get())
    }

    single<AddDeliveryToCartUseCase> {
        AddDeliveryToCartUseCase.Base(get(), get(), get(), get(), get())
    }

    single<ChatsUseCase> {
        ChatsUseCase.Base(get(), get())
    }

    single<SendMessageUseCase> { SendMessageUseCase.Base(get(), get(), get(), get()) }

    single<FetchChatsUseCase> { FetchChatsUseCase.Base(get(), get(), get(), get(), get()) }

    single<FetchMessagesUseCase> { FetchMessagesUseCase.Base(get(), get()) }

    single<FetchOrderChatUseCase> { FetchOrderChatUseCase.Base(get(), get(), get(), get()) }

    single<FetchWantThisDetailsChatUseCase> {
        FetchWantThisDetailsChatUseCase.Base(
            get(),
            get(),
            get(), get()
        )
    }

    viewModel { SupportScreenViewModel(get()) }

    viewModel { BankCardDetailsScreenViewModel(get(), get(), get(), get(), get(), get(), get()) }

    viewModel { BankCardsScreenViewModel(get(), get()) }

    viewModel { ChatScreenViewModel(get(), get()) }

    single<FetchUnconfirmedBankCardsUseCase> {
        FetchUnconfirmedBankCardsUseCase.Base(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    viewModel { ConnectionsScreenViewModel(get()) }

    single<FetchConfirmedBankCardsUseCase> {
        FetchConfirmedBankCardsUseCase.Base(
            get(),
            get(),
            get()
        )
    }

    single<CreateNewWithdrawalUseCase> { CreateNewWithdrawalUseCase.Base(get(), get(), get(), get()) }

    single<FetchWithdrawalTransactionsUseCase> {
        FetchWithdrawalTransactionsUseCase.Base(
            get(),
            get()
        )
    }

    single<FetchCardTemplateUseCase> { FetchCardTemplateUseCase.Base(get(), get(), get()) }

    single<FetchMainCardIdUseCase> { FetchMainCardIdUseCase.Base(get(), get(), get(), get()) }

    single<FetchDocumentChatUseCase> { FetchDocumentChatUseCase.Base(get(), get(), get(), get()) }

    viewModel { WithdrawalScreenViewModel(get(), get(), get()) }

    viewModel { NewWithdrawalScreenViewModel(get(), get(), get()) }

    viewModel {
        WantThisDetailsScreenViewModel(
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
        LocationPermissionScreenViewModel(get(), get(), get())
    }
}