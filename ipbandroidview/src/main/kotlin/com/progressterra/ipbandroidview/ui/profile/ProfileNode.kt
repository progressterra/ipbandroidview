package com.progressterra.ipbandroidview.ui.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class ProfileNode(
    buildContext: BuildContext,
    private val settings: ProfileSettings,
    private val onDetails: () -> Unit,
    private val onSupport: (() -> Unit)?,
    private val onFavorites: (() -> Unit)?,
    private val onOrders: (() -> Unit)?,
    private val onReferral: (() -> Unit)?
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: ProfileViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is ProfileEffect.Support -> onSupport?.invoke()
                is ProfileEffect.OpenDetails -> onDetails()
                is ProfileEffect.Favorites -> onFavorites?.invoke()
                is ProfileEffect.Orders -> onOrders?.invoke()
                is ProfileEffect.Referral -> onReferral?.invoke()
            }
        }
        val state = viewModel.collectAsState()
        ProfileScreen(
            state = state.value,
            interactor = viewModel,
            settings = settings
        )
    }
}