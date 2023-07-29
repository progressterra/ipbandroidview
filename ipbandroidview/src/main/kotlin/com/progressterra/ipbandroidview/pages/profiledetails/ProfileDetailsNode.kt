package com.progressterra.ipbandroidview.pages.profiledetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class ProfileDetailsNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit,
    private val openPhoto: (String) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<ProfileDetailsViewModel>()
        viewModel.collectEffects {
            when (it) {
                is ProfileDetailsEvent.Back -> onBack()
                is ProfileDetailsEvent.OpenPhoto -> openPhoto(it.photo)
            }
        }
        var alreadyLaunched by rememberSaveable {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.refresh()
        }
        val state = viewModel.state.collectAsState().value
        ProfileDetailsScreen(
            state = state,
            useComponent = viewModel
        )
    }
}