package com.progressterra.ipbandroidview.ui.profiledetails

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.ui.profile.ProfileViewModel
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class ProfileDetailsNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val parentViewModel: ProfileViewModel = getViewModel()
        val viewModel: ProfileDetailsViewModel = getViewModel()
        val context = LocalContext.current
        viewModel.collectSideEffect {
            when (it) {
                is ProfileDetailsEffect.Back -> onBack()
                is ProfileDetailsEffect.Logout -> integrationPoint.onRootFinished()
                is ProfileDetailsEffect.Toast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is ProfileDetailsEffect.UpdateUserInfo -> parentViewModel.refresh()
            }
        }
        val state = viewModel.collectAsState()
        ProfileDetailsScreen(
            state = state::value,
            confirmChange = viewModel::confirmChange,
            editEmail = viewModel::editEmail,
            editName = viewModel::editName,
            back = viewModel::back,
            logout = viewModel::logout
        )
    }
}