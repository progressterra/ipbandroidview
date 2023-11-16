package com.progressterra.ipbandroidview.pages.documentdetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.entities.Document
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class DocumentDetailsScreenNode(
    buildContext: BuildContext,
    private val navigation: DocumentDetailsScreenNavigation,
    private val input: Document
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<DocumentDetailsViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is DocumentDetailsScreenEffect.Back -> navigation.onBack()
                is DocumentDetailsScreenEffect.OpenPhoto -> navigation.onPhoto(effect.data)
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(input) {
            viewModel.setup(input)
        }
        DocumentDetailsScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}

