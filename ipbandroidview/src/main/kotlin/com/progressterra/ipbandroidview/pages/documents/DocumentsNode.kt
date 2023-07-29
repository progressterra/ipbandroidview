package com.progressterra.ipbandroidview.pages.documents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.pages.documentdetails.DocumentDetailsState
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class DocumentsNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit,
    private val onDocument: (DocumentDetailsState) -> Unit
) : Node(
    buildContext = buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<DocumentsViewModel>()
        viewModel.collectEffects {
            when (it) {
                is DocumentsScreenEvent.Back -> onBack()
                is DocumentsScreenEvent.OpenDocument -> onDocument(it.item)
            }
        }
        val state = viewModel.state.value
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

