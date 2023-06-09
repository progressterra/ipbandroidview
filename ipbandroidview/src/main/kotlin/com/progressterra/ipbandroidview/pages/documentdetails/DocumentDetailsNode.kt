package com.progressterra.ipbandroidview.pages.documentdetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class DocumentDetailsNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit,
    private val documentDetailsState: DocumentDetailsState,
    private val openPhoto: (String) -> Unit
) : Node(
    buildContext = buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<DocumentDetailsViewModel>()
        viewModel.collectSideEffect {
            when (it) {
                is DocumentDetailsEvent.Back -> onBack()
                is DocumentDetailsEvent.OpenPhoto -> openPhoto(it.image)
            }
        }
        val state = viewModel.collectAsState().value
        DocumentDetails(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}

