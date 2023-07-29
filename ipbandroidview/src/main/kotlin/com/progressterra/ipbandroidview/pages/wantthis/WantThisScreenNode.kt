package com.progressterra.ipbandroidview.pages.wantthis

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class WantThisScreenNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit,
    private val onRequests: () -> Unit,
    private val onPhoto: (String) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<WantThisScreenViewModel>()
        val context = LocalContext.current
        viewModel.collectEffects {
            when (it) {
                is WantThisScreenEvent.Back -> onBack()
                is WantThisScreenEvent.Requests -> onRequests()
                is WantThisScreenEvent.OpenPhoto -> onPhoto(it.url)
                is WantThisScreenEvent.Toast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.state.value
        WantThisScreen(
            modifier = modifier, state = state, useComponent = viewModel
        )
    }
}