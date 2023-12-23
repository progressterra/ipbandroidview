package com.progressterra.ipbandroidview.pages.documents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class DocumentsScreenNode(
    buildContext: BuildContext,
    private val navigation: DocumentsScreenNavigation
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<DocumentsScreenViewModel>()
        viewModel.collectEffects { effect ->
            when (effect) {
                is DocumentsScreenEffect.Back -> navigation.onBack()
                is DocumentsScreenEffect.OpenDocument -> navigation.onDocument(effect.data)
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        DocumentsScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}

