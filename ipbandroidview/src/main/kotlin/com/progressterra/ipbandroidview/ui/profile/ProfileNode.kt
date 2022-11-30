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
    private val onFavorites: (() -> Unit)?,
    private val onOrders: (() -> Unit)?
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: ProfileViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                ProfileEffect.OpenDetails -> onDetails()
                ProfileEffect.Favorites -> onFavorites?.invoke()
                ProfileEffect.Orders -> onOrders?.invoke()
            }
        }
        val state = viewModel.collectAsState()
        ProfileScreen(
            state = state::value,
            openDetails = viewModel::openDetails,
            onOrders = viewModel::onOrders,
            onFavorites = viewModel::onFavorites,
            settings = settings
        )
    }
}