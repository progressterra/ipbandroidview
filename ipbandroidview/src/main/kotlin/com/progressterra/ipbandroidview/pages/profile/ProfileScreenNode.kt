package com.progressterra.ipbandroidview.pages.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class ProfileScreenNode(
    buildContext: BuildContext,
    navigation: ProfileScreenNavigation
) : AbstractNonInputNode<ProfileScreenNavigation, ProfileScreenState, ProfileScreenEffect, ProfileScreenViewModel>(
    buildContext,
    navigation
) {

    override fun mapEffect(effect: ProfileScreenEffect) {
        when (effect) {
            is ProfileScreenEffect.Auth -> navigation.onAuth()
            is ProfileScreenEffect.Favorites -> navigation.onFavorites()
            is ProfileScreenEffect.Logout -> navigation.onLogout()
            is ProfileScreenEffect.Orders -> navigation.onOrders()
            is ProfileScreenEffect.Support -> navigation.onSupport()
            is ProfileScreenEffect.Details -> navigation.onDetails()
            is ProfileScreenEffect.BankCards -> navigation.onBankCards()
            is ProfileScreenEffect.Documents -> navigation.onDocuments()
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<ProfileScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: ProfileScreenState) {
        ProfileScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}