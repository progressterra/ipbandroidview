package com.progressterra.ipbandroidview.pages

import com.progressterra.ipbandroidview.pages.avatarpicker.AvatarPickerScreenViewModel
import com.progressterra.ipbandroidview.pages.bankcarddetails.BankCardDetailsScreenViewModel
import com.progressterra.ipbandroidview.pages.bankcards.BankCardsScreenViewModel
import com.progressterra.ipbandroidview.pages.bonusesdetails.BonusesDetailsScreenViewModel
import com.progressterra.ipbandroidview.pages.cart.CartScreenViewModel
import com.progressterra.ipbandroidview.pages.catalog.CatalogScreenViewModel
import com.progressterra.ipbandroidview.pages.chat.ChatScreenViewModel
import com.progressterra.ipbandroidview.pages.chats.ChatsScreenViewModel
import com.progressterra.ipbandroidview.pages.checklist.ChecklistViewModel
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
import com.progressterra.ipbandroidview.pages.organizationaudits.OrganizationAuditsScreenViewModel
import com.progressterra.ipbandroidview.pages.organizations.OrganizationsViewModel
import com.progressterra.ipbandroidview.pages.overview.OverviewScreenViewModel
import com.progressterra.ipbandroidview.pages.payment.PaymentScreenViewModel
import com.progressterra.ipbandroidview.pages.peoplenearby.PeopleNearbyScreenViewModel
import com.progressterra.ipbandroidview.pages.pfppicker.PfpPickerScreenViewModel
import com.progressterra.ipbandroidview.pages.photo.PhotoScreenViewModel
import com.progressterra.ipbandroidview.pages.profile.ProfileScreenViewModel
import com.progressterra.ipbandroidview.pages.profiledetails.ProfileDetailsScreenViewModel
import com.progressterra.ipbandroidview.pages.readytomeet.ReadyToMeetScreenViewModel
import com.progressterra.ipbandroidview.pages.signin.SignInScreenViewModel
import com.progressterra.ipbandroidview.pages.signup.SignUpScreenViewModel
import com.progressterra.ipbandroidview.pages.support.SupportScreenViewModel
import com.progressterra.ipbandroidview.pages.targetpicker.TargetPickerScreenViewModel
import com.progressterra.ipbandroidview.pages.wantthis.WantThisScreenViewModel
import com.progressterra.ipbandroidview.pages.wantthisdetails.WantThisDetailsScreenViewModel
import com.progressterra.ipbandroidview.pages.wantthisrequests.WantThisRequestsScreenViewModel
import com.progressterra.ipbandroidview.pages.welcome.WelcomeScreenViewModel
import com.progressterra.ipbandroidview.pages.withdrawal.WithdrawalScreenViewModel
import com.progressterra.ipbandroidview.pages.workwatch.WorkWatchScreenViewModel
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
            get()
        )
    }

    viewModel { AvatarPickerScreenViewModel() }

    viewModel { PfpPickerScreenViewModel(get(), get(), get(), get()) }

    viewModel { InfoScreenViewModel(get(), get()) }

    viewModel { InterestsScreenViewModel(get(), get(), get()) }

    viewModel { OccupationScreenViewModel(get(), get(), get()) }

    viewModel { ChatsScreenViewModel(get()) }

    viewModel { PeopleNearbyScreenViewModel(get()) }

    viewModel { CatalogScreenViewModel(get(), get(), get(), get()) }

    viewModel { CartScreenViewModel(get(), get(), get()) }

    viewModel { DocumentsScreenViewModel(get(), get(), get()) }

    viewModel { DocumentDetailsViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }

    viewModel { OrdersScreenViewModel(get()) }

    viewModel { OrderStatusScreenViewModel() }

    viewModel { WantThisRequestsScreenViewModel(get(), get(), get()) }

    viewModel { WantThisScreenViewModel(get(), get(), get(), get(), get(), get()) }

    viewModel { WorkWatchScreenViewModel(get(), get(), get(), get()) }

    viewModel { SupportScreenViewModel(get()) }

    viewModel { BankCardDetailsScreenViewModel(get(), get(), get(), get(), get(), get(), get()) }

    viewModel { BankCardsScreenViewModel(get(), get()) }

    viewModel { ChatScreenViewModel(get(), get()) }

    viewModel { ConnectionsScreenViewModel(get()) }

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

    viewModel {
        TargetPickerScreenViewModel(get(), get())
    }

    viewModel {
        ReadyToMeetScreenViewModel(get(), get(), get())
    }

    viewModel { OrganizationsViewModel(get()) }

    viewModel { OrganizationAuditsScreenViewModel(get(), get()) }

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
            get()
        )
    }

    viewModel { OverviewScreenViewModel(get(), get()) }
}