package com.progressterra.ipbandroidview.pages.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class ProfileScreenNode(
    buildContext: BuildContext,
    private val navigation: ProfileScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<ProfileScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is ProfileScreenEffect.Auth -> navigation.onAuth()
                is ProfileScreenEffect.Favorites -> navigation.onFavorites()
                is ProfileScreenEffect.Logout -> navigation.onLogout()
                is ProfileScreenEffect.Orders -> navigation.onOrders()
                is ProfileScreenEffect.Support -> navigation.onSupport()
                is ProfileScreenEffect.WantThis -> navigation.onWantThis()
                is ProfileScreenEffect.Details -> navigation.onProfileDetails()
                is ProfileScreenEffect.BankCards -> navigation.onBankCards()
                is ProfileScreenEffect.Documents -> navigation.onDocuments()
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        ProfileScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}