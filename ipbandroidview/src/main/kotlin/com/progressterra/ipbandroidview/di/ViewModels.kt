package com.progressterra.ipbandroidview.di

import com.progressterra.ipbandroidview.ui.archive.ArchiveViewModel
import com.progressterra.ipbandroidview.ui.bonuses.BonusesViewModel
import com.progressterra.ipbandroidview.ui.bonusesclarification.BonusesClarificationViewModel
import com.progressterra.ipbandroidview.ui.cart.CartViewModel
import com.progressterra.ipbandroidview.ui.catalog.CatalogViewModel
import com.progressterra.ipbandroidview.ui.checklist.ChecklistViewModel
import com.progressterra.ipbandroidview.ui.city.CityViewModel
import com.progressterra.ipbandroidview.ui.confirmationcode.ConfirmationCodeViewModel
import com.progressterra.ipbandroidview.ui.documents.DocumentsViewModel
import com.progressterra.ipbandroidview.ui.favorites.FavoritesViewModel
import com.progressterra.ipbandroidview.ui.goods.GoodsViewModel
import com.progressterra.ipbandroidview.ui.goodsdetails.GoodsDetailsViewModel
import com.progressterra.ipbandroidview.ui.main.MainViewModel
import com.progressterra.ipbandroidview.ui.mainhaccp.MainHaccpViewModel
import com.progressterra.ipbandroidview.ui.order.OrderViewModel
import com.progressterra.ipbandroidview.ui.orderprocessing.OrderProcessingViewModel
import com.progressterra.ipbandroidview.ui.orders.OrdersViewModel
import com.progressterra.ipbandroidview.ui.organizationaudits.OrganizationAuditsViewModel
import com.progressterra.ipbandroidview.ui.organizations.OrganizationsViewModel
import com.progressterra.ipbandroidview.ui.partner.PartnerViewModel
import com.progressterra.ipbandroidview.ui.photo.PhotoViewModel
import com.progressterra.ipbandroidview.ui.pickuppoint.PickUpPointViewModel
import com.progressterra.ipbandroidview.ui.profile.ProfileViewModel
import com.progressterra.ipbandroidview.ui.profiledetails.ProfileDetailsViewModel
import com.progressterra.ipbandroidview.ui.referral.ReferralViewModel
import com.progressterra.ipbandroidview.ui.signin.SignInViewModel
import com.progressterra.ipbandroidview.ui.signup.SignUpViewModel
import com.progressterra.ipbandroidview.ui.splash.SplashViewModel
import com.progressterra.ipbandroidview.ui.subcatalog.SubCatalogViewModel
import com.progressterra.ipbandroidview.ui.support.SupportViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { MainViewModel(get(), get(), get(), get(), get()) }

    viewModel { ArchiveViewModel() }

    viewModel { DocumentsViewModel(get(), get()) }

    viewModel { SplashViewModel(get()) }

    viewModel { OrganizationsViewModel(get(), get()) }

    viewModel { SignInViewModel(get(), get(), get()) }

    viewModel { SignUpViewModel(get(), get(), get(), get(), get(), get()) }

    viewModel { ConfirmationCodeViewModel(get(), get(), get()) }

    viewModel { OrganizationAuditsViewModel(get(), get()) }

    viewModel { CityViewModel(get(), get(), get(), get(), get()) }

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

    viewModel { ProfileDetailsViewModel(get(), get(), get(), get(), get(), get()) }

    viewModel { CatalogViewModel(get()) }

    viewModel { SubCatalogViewModel() }

    viewModel { GoodsViewModel(get(), get(), get()) }

    viewModel { GoodsDetailsViewModel(get(), get(), get(), get()) }

    viewModel { FavoritesViewModel(get(), get()) }

    viewModel { CartViewModel(get(), get(), get(), get()) }

    viewModel { BonusesViewModel(get(), get()) }

    viewModel { BonusesClarificationViewModel() }

    viewModel {
        OrderViewModel(
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

    viewModel { OrdersViewModel(get()) }

    viewModel { OrderProcessingViewModel() }

    viewModel { PickUpPointViewModel(get()) }

    viewModel { SupportViewModel(get(), get(), get()) }

    viewModel { ReferralViewModel(get(), get(), get()) }

    viewModel { PartnerViewModel(get(), get()) }

    viewModel { MainHaccpViewModel(get(), get(), get()) }
}
