package com.progressterra.ipbandroidview.pages.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class ProfileNode(
    buildContext: BuildContext,
    private val onAuth: () -> Unit,
    private val onFavorites: () -> Unit,
    private val onLogout: () -> Unit,
    private val onOrders: () -> Unit,
    private val onSupport: () -> Unit,
    private val onDetails: () -> Unit,
    private val onBankCards: () -> Unit,
    private val onDocuments: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<ProfileViewModel>()
        viewModel.collectEffects {
            when (it) {
                is ProfileEvent.Auth -> onAuth()
                is ProfileEvent.Favorites -> onFavorites()
                is ProfileEvent.Logout -> onLogout()
                is ProfileEvent.Orders -> onOrders()
                is ProfileEvent.Support -> onSupport()
                is ProfileEvent.Details -> onDetails()
                is ProfileEvent.BankCards -> onBankCards()
                is ProfileEvent.Documents -> onDocuments()
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.state.collectAsState().value
        ProfileScreen(
            state = state,
            useComponent = viewModel
        )
    }
}