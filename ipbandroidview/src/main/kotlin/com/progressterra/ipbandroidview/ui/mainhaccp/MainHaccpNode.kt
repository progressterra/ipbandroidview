package com.progressterra.ipbandroidview.ui.mainhaccp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.entities.Partner
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class MainHaccpNode(
    buildContext: BuildContext,
    private val onArchive: (title: String, List<Document>) -> Unit,
    private val onPartner: (Partner) -> Unit
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: MainHaccpViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is MainHaccpEffect.Archive -> onArchive(it.title, it.archived)
                is MainHaccpEffect.OpenPartner -> onPartner(it.partner)
            }
        }
        val state = viewModel.collectAsState()
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        MainHaccpScreen(
            state = state.value, interactor = viewModel
        )
    }
}