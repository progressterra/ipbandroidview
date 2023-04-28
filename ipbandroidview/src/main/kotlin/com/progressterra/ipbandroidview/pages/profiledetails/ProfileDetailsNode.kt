package com.progressterra.ipbandroidview.pages.profiledetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.entities.MultisizedImage
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class ProfileDetailsNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit,
    private val openPhoto: (MultisizedImage) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<ProfileDetailsViewModel>()
        viewModel.collectSideEffect {
            when (it) {
                is ProfileDetailsEvent.Back -> onBack()
                is ProfileDetailsEvent.OpenPhoto -> openPhoto(it.photo)
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.collectAsState().value
        ProfileDetailsScreen(
            state = state,
            useComponent = viewModel
        )
    }
}