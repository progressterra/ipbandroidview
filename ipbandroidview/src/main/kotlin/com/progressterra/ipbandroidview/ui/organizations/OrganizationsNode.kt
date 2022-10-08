package com.progressterra.ipbandroidview.ui.organizations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.ui.root.RootViewModel
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Suppress("unused")
class OrganizationsNode(
    buildContext: BuildContext
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val rootViewModel: RootViewModel = getViewModel()
        rootViewModel.configure(
            title = stringResource(id = R.string.organizations),
            bottomBarVisibility = true
        )
        val viewModel: OrganizationsViewModel = getViewModel()
        val state = viewModel.collectAsState().value
        OrganizationsScreen(state = state, interactor = viewModel)
    }
}