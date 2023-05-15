package com.progressterra.ipbandroidview.pages.profile

import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class ProfileNode(
    buildContext: BuildContext,
    private val onAuth: () -> Unit,
    private val onFavorites: () -> Unit,
    private val onLogout: () -> Unit,
    private val onOrders: () -> Unit,
    private val onSupport: () -> Unit,
    private val onDetails: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<ProfileViewModel>()
        viewModel.collectSideEffect {
            when (it) {
                is ProfileEvent.Auth -> onAuth()
                is ProfileEvent.Favorites -> onFavorites()
                is ProfileEvent.Logout -> onLogout()
                is ProfileEvent.Orders -> onOrders()
                is ProfileEvent.Support -> onSupport()
                is ProfileEvent.Details -> onDetails()
            }
        }
        var alreadyLaunched by rememberSaveable {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.refresh()
        }
        val state = viewModel.collectAsState().value
        ProfileScreen(
            state = state,
            useComponent = viewModel
        )
    }
}