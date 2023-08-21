package com.progressterra.ipbandroidview.pages.bankcarddetails

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.entities.Document
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class BankCardDetailsScreenNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit,
    private val onPhoto: (String) -> Unit,
    private val details: Document
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<BankCardDetailsScreenViewModel>()
        val context = LocalContext.current
        viewModel.collectEffects {
            when (it) {
                is BankCardDetailsScreenEvent.Back -> onBack()
                is BankCardDetailsScreenEvent.OpenPhoto -> onPhoto(it.image)
                is BankCardDetailsScreenEvent.Toast -> Toast.makeText(
                    context,
                    it.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        LaunchedEffect(details) {
            viewModel.setup(details)
        }
        val state = viewModel.state.collectAsState().value
        BankCardDetailsScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}