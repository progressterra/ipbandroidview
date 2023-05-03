package com.progressterra.ipbandroidview.ui.agreement

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class AgreementNode(
    buildContext: BuildContext, private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: AgreementViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is AgreementEvent.Back -> onBack()
            }
        }
        AgreementScreen(
            useComponent = viewModel
        )
    }
}