package com.progressterra.ipbandroidview.ui.partner

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.entities.Partner
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class PartnerNode(
    buildContext: BuildContext,
    private val partner: Partner,
    private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: PartnerViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is PartnerEffect.Back -> onBack()
            }
        }
        var alreadyLaunched by rememberSaveable(partner) {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.setPartner(partner)
        }
        val state = viewModel.collectAsState()
        PartnerScreen(
            state = state.value,
            interactor = viewModel
        )
    }
}