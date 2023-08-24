package com.progressterra.ipbandroidview.pages.wantthisdetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.entities.Document
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class WantThisDetailsScreenNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit,
    private val documentDetailsState: Document,
    private val openPhoto: (String) -> Unit,
    private val onGoodsDetails: (String) -> Unit
) : Node(
    buildContext = buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<WantThisDetailsScreenViewModel>()
        viewModel.collectEffects {
            when (it) {
                is WantThisDetailsScreenSideEffect.Back -> onBack()
                is WantThisDetailsScreenSideEffect.OpenPhoto -> openPhoto(it.image)
                is WantThisDetailsScreenSideEffect.GoodsDetails -> onGoodsDetails(it.id)
            }
        }
        LaunchedEffect(documentDetailsState) {
            viewModel.setup(documentDetailsState)
        }
        val state = viewModel.state.collectAsState().value
        WantThisDetailsScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}

