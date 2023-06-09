package com.progressterra.ipbandroidview.pages.documents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.pages.documentdetails.DocumentDetailsState
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

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
        viewModel.collectSideEffect {
            when (it) {
                is DocumentsScreenEvent.Back -> onBack()
                is DocumentsScreenEvent.OpenDocument -> onDocument(it.item)
            }
        }
        val state = viewModel.collectAsState().value
        var alreadyLaunched by rememberSaveable {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.refresh()
        }
        DocumentsScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}

